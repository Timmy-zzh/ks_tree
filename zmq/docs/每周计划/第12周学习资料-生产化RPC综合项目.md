# 第 12 周学习资料：生产化 RPC 综合项目

> 本资料配套计划：[第 12 周：生产化 RPC 综合项目](第12周-生产化RPC综合项目.md)。

## 1. 项目目标

本周将前 11 周知识组合为一个可验证的小型 RPC 系统。目标不是做出完整商业框架，而是通过明确协议、故障实验和指标，展示系统在真实失败场景中的行为。

完成后应有：

- 一个客户端、Gateway、worker pool 和业务处理模块；
- 明确的多帧协议和 request ID；
- 超时、有限重试、幂等和重复请求处理；
- HWM、最大消息、优雅退出和安全认证；
- monitor、结构化日志、指标和自动化验收记录。

## 2. 推荐架构

```text
Client DEALER/REQ
        |
        | tcp://
        v
RPC Gateway ROUTER
        |
        | inproc://backend
        v
Gateway DEALER
        |
        +--> Worker 1
        +--> Worker 2
        +--> Worker 3
        |
        v
业务处理与幂等存储
```

简化实现可以先用 REQ/REP 跑通，再改为 ROUTER/DEALER，以支持并发未完成请求和 worker 路由。参考：

- `zguide/examples/C++/rrclient.cpp`
- `zguide/examples/C++/rrbroker.cpp`
- `zguide/examples/C++/rrworker.cpp`
- `test_zeromq/service_router.cpp`
- `test_zeromq/client_dealer.cpp`

## 3. 先定义协议

请求至少包含：

```text
version | request_id | operation | payload
```

响应至少包含：

```text
version | request_id | status | error_code | payload
```

建议状态：

```text
OK
INVALID_ARGUMENT
UNAUTHORIZED
NOT_FOUND
BUSY
TIMEOUT
INTERNAL_ERROR
UNKNOWN
```

协议要求：

- request ID 在重试期间保持不变；
- 响应必须回传 request ID；
- 版本、frame 数、每帧大小和 payload 总大小都要校验；
- 未知操作返回稳定错误码；
- 不把内部堆栈和密钥写入响应或日志。

multipart 参考：`cppzmq-4.11.0/examples/multipart_messages.cpp`。

## 4. 推荐实施里程碑

### 里程碑一：最小 RPC

- 客户端发送请求；
- Gateway 路由到一个 worker；
- worker 返回响应；
- 完成正常请求、参数错误和未知操作。

### 里程碑二：并发和内部通信

- Gateway 使用 ROUTER/DEALER；
- 多个 worker 使用独立 Socket；
- 使用 `inproc://`；
- 增加 request ID 和响应匹配；
- 测试慢 worker 和 worker 退出。

### 里程碑三：可靠性

- 设置接收和发送超时；
- 有限重试、指数退避和随机抖动；
- 增加去重表或数据库唯一约束；
- 处理响应丢失后的 `UNKNOWN` 和状态查询。

### 里程碑四：保护和可观测性

- 设置 HWM、`ZMQ_MAXMSGSIZE` 和 `ZMQ_LINGER`；
- 增加 CURVE；
- 增加 Socket monitor；
- 输出结构化日志和延迟指标；
- 完成优雅退出。

### 里程碑五：故障验收

- 服务端重启；
- worker 崩溃；
- 断网恢复；
- 重复请求；
- HWM 满；
- 超大消息；
- 错误密钥；
- 优雅停止。

## 5. 可靠性设计

客户端请求状态：

```text
NEW -> SENT -> WAITING -> SUCCESS
                    |
                    +-> TIMEOUT -> RETRY -> SUCCESS
                                      |
                                      +-> UNKNOWN
```

服务端幂等流程：

1. 读取 request ID；
2. 校验参数；
3. 查询已处理记录；
4. 已完成则返回历史结果；
5. 未处理则在业务事务中执行并保存结果；
6. 响应丢失时，重复请求返回同一个结果。

不要宣称 ZeroMQ 自动 exactly-once。项目应明确采用“至少一次传输 + request ID 去重 + 状态查询”还是其他语义。

## 6. 安全、限流和关闭

安全：

- Gateway 配置 CURVE；
- 服务端校验客户端身份；
- 未授权请求返回统一错误；
- 私钥不进代码、日志和版本库；
- 指定监听地址并配合防火墙。

限流：

- 设置 `ZMQ_SNDHWM`、`ZMQ_RCVHWM`；
- 设置 `ZMQ_MAXMSGSIZE`；
- 为请求和连接设置上限；
- HWM 满时返回 `BUSY` 或有限等待，不无限堆积。

关闭：

```text
停止接收新请求
  -> 通知 Gateway 和 worker
  -> 完成或取消在途任务
  -> join 线程
  -> 设置合适的 LINGER
  -> 关闭 Socket 和 Context
```

`LINGER=0` 可以快速退出，但可能丢弃未发送消息；具体取值应写入项目设计说明。

## 7. 日志和指标

结构化日志至少包含：

```text
时间戳、request_id、客户端身份、操作名、状态、错误码、耗时、worker、重试次数
```

指标至少包含：

- 请求和响应总数；
- 成功、业务失败、超时、重试和重复请求数；
- 当前连接、重连和握手失败数；
- HWM/队列告警；
- P50/P95/P99 延迟；
- 消息大小；
- worker 处理时间；
- 进程 CPU、内存和文件描述符。

参考监控：`cppzmq-4.11.0/tests/monitor.cpp`。

## 8. 验收测试表

| 场景 | 预期行为 | 实际结果 | 通过 |
| --- | --- | --- | --- |
| 正常请求 | 返回 OK 且 ID 匹配 | | |
| 参数错误 | 返回 INVALID_ARGUMENT | | |
| 服务端重启 | 客户端有限重试或返回 UNKNOWN | | |
| 响应丢失 | 重试不重复执行 | | |
| worker 崩溃 | 任务有明确失败或重试结果 | | |
| HWM 满 | 限流、超时或 BUSY，不无限增长 | | |
| 超大消息 | 拒绝并记录原因 | | |
| 重复请求 | 返回历史结果，不重复副作用 | | |
| 错误密钥 | 握手/认证失败并告警 | | |
| 优雅停止 | 在途任务和退出时间符合设计 | | |

## 9. 项目目录建议

```text
rpc_project/
├── CMakeLists.txt
├── protocol.hpp          # frame、状态和错误码
├── client.cpp
├── gateway.cpp
├── worker.cpp
├── idempotency_store.hpp
├── monitor.cpp
├── config/
├── tests/
└── README.md
```

仓库中没有现成的完整生产化 RPC 成品；以上目录是学习项目的建议骨架，需要根据实验逐步实现。

## 10. 最终验收问题

必须通过实验回答：

1. 服务端重启后客户端如何恢复？
2. 处理成功但响应丢失时如何处理？
3. worker 崩溃后任务如何处理？
4. HWM 满时如何限流或失败？
5. 消息过大时如何拒绝？
6. 重复请求如何避免重复业务？
7. 认证失败如何记录和告警？
8. 如何优雅停止并控制 `LINGER`？
9. 如何区分进程、连接和业务健康？
10. 如何通过指标发现排队、重连和延迟问题？

## 11. 学习记录和交付物

```markdown
## 第 12 周项目记录

- 项目日期：
- 架构图：
- 协议说明：
- 构建命令：
- 正常请求结果：
- 故障注入结果：
- 幂等方案：
- 安全方案：
- 监控指标：
- 压测结果：
- 已知限制：
- 后续改进：
```

最终至少提交：架构图、协议文档、源码、构建说明、单元测试、压力测试、故障验收表和设计总结。

## 12. 延伸阅读

- `docs/每周计划/第02周学习资料-REQ-REP与基线代码.md`
- `docs/每周计划/第08周学习资料-可靠性超时重试与幂等.md`
- `zguide/examples/C++/rrbroker.cpp`
- `zguide/examples/C++/rrworker.cpp`
- `libzmq-4.3.5/tests/test_security_curve.cpp`
- `libzmq-4.3.5/tests/test_monitor.cpp`
