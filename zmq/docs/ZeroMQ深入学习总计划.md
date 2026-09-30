# ZeroMQ 深入学习总计划

> 本文档是 ZeroMQ 学习路线的总纲，回答“为什么学习、整体学什么、按什么顺序学习、最终达到什么能力”。每周的具体任务、实验和验收要求见 [`每周计划/`](每周计划/)。

## 一、学习目标

用 12 周系统掌握 ZeroMQ/libzmq/cppzmq，从能够编写基础通信程序，提升到能够：

- 熟练使用 `context_t`、`socket_t`、`message_t`、`bind`、`connect`、`send`、`recv`、multipart、poll 和 socket option；
- 解释消息从业务线程到 Socket、Pipe、Session、Engine、ZMTP、Transport、操作系统 Socket 和 TCP/IP 网络的完整流转；
- 根据业务需求选择 REQ/REP、DEALER/ROUTER、PUSH/PULL、PUB/SUB、XPUB/XSUB、PAIR 等通信模式；
- 设计超时、重试、request ID、ACK、幂等、去重、背压、限流、重连、心跳和优雅退出；
- 配置 CURVE/ZAP 安全认证，建立日志、监控、指标和故障排查能力；
- 沿着 `send` 和 `recv` 调用路径阅读 libzmq 源码；
- 完成一个带工作池、协议、可靠性、安全和监控能力的小型 RPC 系统。

## 二、学习周期和投入

推荐周期为 12 周，每周投入 8～12 小时，每天 1～2 小时。

如果每周只能投入 4～6 小时，可以扩展为 20～24 周，但不要跳过故障实验和阶段验收。

### 每周通用节奏

| 时间 | 活动 | 产出 |
|---|---|---|
| 周一 | 阅读概念和官方资料 | 概念清单、架构图 |
| 周二 | 编写最小程序 | 可运行代码 |
| 周三 | 增加参数、异常和边界处理 | 对比代码、错误记录 |
| 周四 | 故障注入 | 实验数据 |
| 周五 | 阅读相关源码 | 调用路径和源码笔记 |
| 周六 | 综合实验或压测 | 实验报告 |
| 周日 | 总结和下周准备 | 周总结、问题清单 |

每天 90 分钟可安排为：

```text
20 分钟：阅读官方资料
40 分钟：编码或修改实验
20 分钟：故障注入和现象观察
10 分钟：记录结论
```

## 三、总体学习方法

采用下面的学习闭环：

```text
理解概念
  -> 编写最小程序
  -> 制造异常场景
  -> 观察现象
  -> 阅读相关源码
  -> 总结规律
  -> 重构为工程代码
```

每学习一个重要知识点，至少留下三项成果：

1. 一张数据流、状态机或架构图；
2. 一个只验证单一知识点的最小程序；
3. 一份记录预期现象、实际现象、原因和结论的实验笔记。

总计划用于掌握全局路线，周计划用于执行具体工作。每周开始时阅读对应文件，每周结束时完成其中的输出和验收，再更新本总计划的知识点清单。

## 四、学习阶段和路线图

| 阶段 | 周次 | 学习主题 | 阶段目标 |
|---|---:|---|---|
| 基础使用 | 1～3 | 基础概念、REQ/REP、消息帧和序列化 | 能编译运行并解释基础消息流 |
| 模式与并发 | 4～5 | 通信模式、拓扑、线程和进程 | 能选择模式并设计 worker 架构 |
| 行为与可靠性 | 6～8 | HWM、背压、重连、超时、重试和幂等 | 能分析失败语义并设计恢复机制 |
| 工程化 | 9～10 | 安全、轮询、监控、性能和故障排查 | 能建立生产运行所需的安全和指标体系 |
| 深入实现 | 11 | libzmq 源码阅读 | 能跟踪 send/recv 调用链 |
| 综合落地 | 12 | 生产化 RPC 项目 | 能完成并验证一个可运行原型 |

### 12 周路线图

| 周次 | 主题 | 主要产出 |
|---:|---|---|
| 1 | 基础概念和环境 | 能编译运行基础示例，建立架构图 |
| 2 | REQ/REP 与基线代码 | 状态机图、事件时序图和基线实验记录 |
| 3 | 消息帧、多段消息和序列化 | 应用层多帧协议说明 |
| 4 | 通信模式和拓扑 | Socket 类型对比和模式选择表 |
| 5 | 线程、进程和内部通信 | worker 架构和线程边界说明 |
| 6 | 队列、高水位和背压 | HWM、阻塞、丢失和 `EAGAIN` 实验数据 |
| 7 | 连接生命周期、重连和心跳 | 连接状态机和故障时间线 |
| 8 | 可靠性、超时、重试和幂等 | 可靠性等级和恢复策略 |
| 9 | 安全、认证和访问控制 | CURVE/ZAP 配置及威胁模型 |
| 10 | 轮询、监控、性能和故障排查 | 指标定义和性能基线 |
| 11 | libzmq 源码阅读 | `send`/`recv` 调用链和源码笔记 |
| 12 | 生产化 RPC 综合项目 | 可运行的 RPC 原型和验收报告 |

详细内容：

1. [第 01 周：基础概念与环境](每周计划/第01周-基础概念与环境.md)
2. [第 02 周：REQ/REP 与基线代码](每周计划/第02周-REQ-REP与基线代码.md)
3. [第 03 周：消息帧、多段消息与序列化](每周计划/第03周-消息帧多段消息与序列化.md)
4. [第 04 周：通信模式与拓扑](每周计划/第04周-通信模式与拓扑.md)
5. [第 05 周：线程、进程与内部通信](每周计划/第05周-线程进程与内部通信.md)
6. [第 06 周：队列、高水位与背压](每周计划/第06周-队列高水位与背压.md)
7. [第 07 周：连接生命周期、重连与心跳](每周计划/第07周-连接生命周期重连与心跳.md)
8. [第 08 周：可靠性、超时、重试与幂等](每周计划/第08周-可靠性超时重试与幂等.md)
9. [第 09 周：安全、认证与访问控制](每周计划/第09周-安全认证与访问控制.md)
10. [第 10 周：轮询、监控、性能与故障排查](每周计划/第10周-轮询监控性能与故障排查.md)
11. [第 11 周：libzmq 源码阅读](每周计划/第11周-libzmq源码阅读.md)
12. [第 12 周：生产化 RPC 综合项目](每周计划/第12周-生产化RPC综合项目.md)

## 五、必须建立的核心认知

### 1. ZeroMQ 的定位

ZeroMQ/libzmq 是嵌入业务进程中的高性能消息通信库，不是默认独立运行的消息服务器。它提供 Socket 抽象、消息队列、通信模式、路由、异步 I/O、连接管理和可选安全能力。

### 2. 架构和数据流

```text
业务线程
  -> ZeroMQ Socket
  -> Pipe
  -> Session
  -> Engine
  -> ZMTP
  -> Transport
  -> 操作系统 Socket
  -> TCP/IP 网络
```

需要能够解释：

- Context 管理 ZeroMQ 运行环境和 I/O 线程；
- I/O 线程负责网络 I/O，不负责业务处理；
- Socket 包含状态机、队列和路由能力；
- Pipe 连接应用线程和 ZeroMQ 内部线程；
- Session 管理逻辑连接；
- Engine 负责协议和底层流；
- Transport 对应 `tcp://`、`ipc://`、`inproc://` 等传输方式。

### 3. 库保证和业务保证

ZeroMQ 默认不提供：

- 磁盘持久化；
- 事务；
- 消费进度；
- exactly-once；
- 业务 ACK；
- 崩溃后的业务状态恢复。

必须始终区分：

```text
send 成功，不等于对端收到；
对端收到，不等于业务处理完成；
TCP 可靠，不等于业务可靠；
自动重连，不等于消息自动恢复。
```

### 4. 线程和进程边界

- Context 可以跨线程共享；
- 普通 Socket 应归属单一应用线程；
- 业务线程和 ZeroMQ I/O 线程职责不同；
- 跨线程应使用 `inproc://` 或线程安全队列；
- 跨进程使用 `ipc://` 或 `tcp://`，不能直接共享 Socket；
- fork 后不应继续复用 fork 前创建的 Context 和 Socket。

## 六、环境和仓库资料

本仓库已经包含适合本计划的源码、示例和依赖目录：

| 路径 | 用途 |
|---|---|
| `zmq/test_zeromq/service_rep.cpp` | REP 服务端基线示例 |
| `zmq/test_zeromq/client_req.cpp` | REQ 客户端基线示例 |
| `zmq/test_zeromq/service_pub.cpp` | PUB 发布端示例 |
| `zmq/test_zeromq/client_sub.cpp` | SUB 订阅端示例 |
| `zmq/test_zeromq/service_push.cpp` | PUSH 任务发布端示例 |
| `zmq/test_zeromq/client_pull.cpp` | PULL 任务接收端示例 |
| `zmq/test_zeromq/service_router.cpp` | ROUTER 服务端示例 |
| `zmq/test_zeromq/client_dealer.cpp` | DEALER 客户端示例 |
| `zmq/test_zeromq/CMakeLists.txt` | 本地示例构建配置 |
| `zmq/test_zeromq/build.sh` | 本地构建脚本 |
| `zmq/cppzmq-4.11.0/README.md` | cppzmq API 和 CMake 参考 |
| `zmq/cppzmq-4.11.0/examples/` | cppzmq 官方 C++ 示例 |
| `zmq/libzmq-4.3.5/doc/` | libzmq API 文档 |
| `zmq/libzmq-4.3.5/src/` | libzmq 实现源码 |
| `zmq/zguide/` | ZeroMQ Guide 本地资料 |

每次实验先确认：

- libzmq、cppzmq、编译器和 CMake 版本；
- `zmq/test_zeromq/CMakeLists.txt` 当前启用的目标；
- 端口是否被占用；
- 防火墙是否允许测试端口；
- 构建目录是否包含旧生成文件。

当前示例的 CMake 文件使用本地硬编码 libzmq 路径，可以先用于学习；第 12 周再考虑改进为 `find_package` 或统一依赖管理。

## 七、全局知识点清单

### 基础概念

- [ ] ZeroMQ 定位
- [ ] libzmq、cppzmq、ZMTP 关系
- [ ] Context
- [ ] I/O 线程
- [ ] Socket
- [ ] Message
- [ ] Endpoint
- [ ] Transport
- [ ] bind/connect
- [ ] tcp/ipc/inproc
- [ ] Socket 线程归属

### 消息和协议

- [ ] 单帧消息
- [ ] multipart
- [ ] `SNDMORE`
- [ ] `RCVMORE`
- [ ] identity frame
- [ ] topic frame
- [ ] 空 frame
- [ ] 二进制消息
- [ ] 序列化
- [ ] 版本兼容
- [ ] 最大消息大小
- [ ] 参数校验

### 通信模式

- [ ] REQ/REP
- [ ] PUSH/PULL
- [ ] PUB/SUB
- [ ] XPUB/XSUB
- [ ] DEALER/ROUTER
- [ ] PAIR
- [ ] 工作池
- [ ] 代理
- [ ] 发布订阅代理

### 队列和性能

- [ ] SNDHWM
- [ ] RCVHWM
- [ ] SNDTIMEO
- [ ] RCVTIMEO
- [ ] DONTWAIT
- [ ] EAGAIN
- [ ] LINGER
- [ ] IMMEDIATE
- [ ] MAXMSGSIZE
- [ ] 背压
- [ ] 慢消费者
- [ ] 吞吐量
- [ ] P50/P95/P99
- [ ] 内存和文件描述符

### 连接和可靠性

- [ ] 自动重连
- [ ] 重连间隔
- [ ] 握手超时
- [ ] 心跳
- [ ] 连接监控
- [ ] 服务端重启
- [ ] 客户端重启
- [ ] 网络断开
- [ ] 请求超时
- [ ] 有限重试
- [ ] 指数退避
- [ ] request ID
- [ ] ACK
- [ ] 幂等
- [ ] 去重
- [ ] 业务状态查询
- [ ] 至少一次
- [ ] exactly-once 的限制

### 线程和进程

- [ ] Context 跨线程共享
- [ ] Socket 单线程归属
- [ ] I/O 线程与业务线程
- [ ] worker pool
- [ ] `inproc://`
- [ ] `ipc://`
- [ ] `tcp://`
- [ ] fork 边界
- [ ] 优雅退出
- [ ] 信号处理

### 安全

- [ ] NULL security
- [ ] CURVE
- [ ] ZAP
- [ ] 密钥管理
- [ ] 身份认证
- [ ] 访问控制
- [ ] 防火墙
- [ ] 网络隔离
- [ ] 日志脱敏
- [ ] 连接和请求限流

### 源码和运维

- [ ] `zmq_send` 调用链
- [ ] `zmq_recv` 调用链
- [ ] Socket 状态机
- [ ] Pipe
- [ ] Session
- [ ] Engine
- [ ] ZMTP
- [ ] TCP Engine
- [ ] socket monitor
- [ ] 连接事件
- [ ] 握手事件
- [ ] 失败注入
- [ ] 压力测试
- [ ] 版本锁定
- [ ] 生产检查表

## 八、阶段里程碑

### 第 4 周结束

能够写出和解释常见通信模式，理解 REQ/REP 状态机、消息帧以及 bind/connect。

### 第 8 周结束

能够分析 HWM、超时、重连、`EAGAIN`，并设计 request ID、重试和幂等方案。

### 第 12 周结束

能够实现一个带 ROUTER/DEALER 工作池、协议版本、可靠性、安全和监控的小型 RPC 系统，并解释消息从业务线程到网络再到对端业务线程的完整流转。

## 九、常见误区

1. **把 ZeroMQ 当成独立消息队列服务器**：默认没有独立 Broker，也没有自动磁盘持久化。
2. **认为 `send` 成功就代表可靠送达**：通常只代表消息被本地 ZeroMQ 接受。
3. **认为 TCP 可靠就等于业务可靠**：TCP 不能保证业务响应、事务和重试结果。
4. **所有 RPC 都使用 REP**：异步、并发、路由和工作池通常应考虑 DEALER/ROUTER。
5. **多个线程共享一个 Socket**：建议每个线程拥有自己的 Socket，通过 `inproc://` 通信。
6. **只测试正常路径**：必须测试断线、重连、HWM、超时、重试、关闭、慢消费者和重复请求。
7. **忽略版本**：实验和生产环境应锁定 libzmq/cppzmq 版本。

## 十、最终综合项目

第 12 周完成一个小型生产化 RPC 系统：

```text
客户端
  |
  | REQ 或 DEALER
  v
RPC Gateway
  |
  | ROUTER / DEALER + inproc
  v
Worker Pool
  |
  v
业务处理模块
```

项目至少包含：

- request ID 和多帧协议；
- JSON、MessagePack 或 Protobuf；
- 参数校验、错误码和响应状态；
- 接收和发送超时；
- 有限重试、指数退避、幂等和去重；
- HWM、最大消息大小和优雅退出；
- CURVE 认证；
- socket monitor、结构化日志和指标；
- 单元测试、压力测试、断网恢复、服务端崩溃恢复和重复请求测试。

项目验收问题见 [第 12 周计划](每周计划/第12周-生产化RPC综合项目.md)。

## 十一、推荐资料

### 本地资料

- `zmq/zguide/README.md`；
- `zmq/zguide/part1.txt`；
- `zmq/zguide/chapter1.txt`；
- `zmq/zguide/chapter2.txt`；
- `zmq/zguide/articles/multithreading.md`；
- `zmq/zguide/articles/reliability.md`；
- `zmq/cppzmq-4.11.0/README.md`；
- `zmq/cppzmq-4.11.0/examples/`；
- `zmq/libzmq-4.3.5/doc/`；
- `zmq/libzmq-4.3.5/src/`。

### 官方在线资料

- [ZeroMQ Guide](https://zguide.zeromq.org/)
- [libzmq 官方仓库](https://github.com/zeromq/libzmq)
- [cppzmq 官方仓库](https://github.com/zeromq/cppzmq)
- [libzmq API 文档](https://zeromq.github.io/libzmq/)
- [ZeroMQ Socket API](https://zeromq.org/socket-api/)
- [ZMTP 规范](https://rfc.zeromq.org/spec/23/)
- [REQ/REP 规范](https://rfc.zeromq.org/spec/28/)
