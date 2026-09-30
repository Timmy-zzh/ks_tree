# 第 11 周学习资料：libzmq 源码阅读

> 本资料配套计划：[第 11 周：libzmq 源码阅读](第11周-libzmq源码阅读.md)。

## 1. 学习目标

本周不追求读完整个 libzmq，而是沿着一条真实消息路径理解对象和线程边界。完成后能够：

- 从公共 API 找到 Context 和 Socket 的实现入口；
- 跟踪 `send`、`recv` 到 Socket 类型状态机的路径；
- 解释 Pipe、Session、Engine 和 I/O thread 的职责；
- 将文档中的消息模型与源码对象对应起来；
- 使用搜索、调用链记录和测试用例辅助阅读。

## 2. 源码目录

源码根目录：`libzmq-4.3.5/`。

优先阅读：

```text
include/zmq.h
src/zmq.cpp
src/ctx.cpp
src/socket_base.cpp
src/req.cpp
src/rep.cpp
src/dealer.cpp
src/router.cpp
src/pipe.cpp
src/session_base.cpp
src/io_thread.cpp
src/tcp.cpp
src/tcp_engine.cpp
src/zmtp_engine.cpp
src/options.cpp
```

不要一开始从所有文件阅读。先用第 2 周 REQ/REP 示例提出一个具体问题，例如“REP 为什么不能连续 recv？”

## 3. 发送和接收路径

发送方向：

```text
zmq_send
  -> socket_base
  -> Socket 类型状态机
  -> pipe
  -> session
  -> engine
  -> ZMTP
  -> TCP
```

接收方向：

```text
TCP
  -> engine
  -> session
  -> pipe
  -> Socket 接收队列
  -> zmq_recv
  -> 业务线程
```

这是一张学习导航图，不应当把它当作每个版本每条分支都完全相同的调用栈。阅读时以当前 `libzmq-4.3.5` 源码为准。

## 4. 推荐阅读顺序

### 第一步：公共 API

从 `include/zmq.h` 找到 `zmq_send`、`zmq_recv`、`zmq_socket`、`zmq_ctx_new` 的声明，再在 `src/zmq.cpp` 查找实现。

### 第二步：Context 和 Socket

阅读 `ctx.cpp`、`socket_base.cpp`，回答：

- Context 如何创建 I/O 线程？
- Socket 如何保存 options？
- Socket 如何连接到 Pipe？
- 关闭和终止的边界是什么？

### 第三步：REQ/REP 状态机

阅读 `req.cpp` 和 `rep.cpp`，重点找：

- 发送前后状态变量；
- 接收前后状态变量；
- 不符合顺序时的错误分支；
- 多帧 envelope 如何处理。

配合 `tests/test_spec_req.cpp`、`test_spec_rep.cpp` 对照预期行为。

### 第四步：内部传输对象

阅读 `pipe.cpp`、`session_base.cpp`、`io_thread.cpp`，建立关系：

```text
Socket <-> Pipe <-> Session <-> Engine <-> Transport
```

Pipe 连接 Socket 与内部会话，Session 管理连接会话，Engine 对接具体传输和协议，I/O thread 驱动底层事件。

### 第五步：TCP 和 ZMTP

再读 `tcp.cpp`、`tcp_engine.cpp`、`zmtp_engine.cpp` 和 `options.cpp`，追踪 Endpoint、连接、握手和 Socket 选项如何进入实现。

## 5. 使用工具阅读源码

在源码目录执行：

```bash
rg -n "zmq_send|zmq_recv|process_attach|xsend|xrecv" src include
rg -n "ZMQ_SNDHWM|ZMQ_HEARTBEAT|ZMQ_CURVE" src options.cpp include
```

阅读每个命中点时记录文件、函数和调用者，不要只复制大段代码。必要时使用调试构建和断点，比较 `service_rep.cpp` 的一次发送与 `test_spec_rep.cpp` 的状态测试。

## 6. 阅读记录模板

每次只跟一条路径：

```markdown
### 路径：REP 接收请求

- 入口函数：
- 调用者：
- 当前线程：
- 消息对象位置：
- 状态机变化：
- Pipe/队列变化：
- 错误分支：
- 连接断开分支：
- 对应的公开 API 语义：
- 我的疑问：
```

## 7. 验证实验

选择一个实验对照源码：

1. 连续两次 REQ send；
2. 连续两次 REP recv；
3. HWM 触发；
4. TCP 连接断开；
5. monitor 事件产生；
6. `inproc` 与 TCP 的传输差异。

参考测试：

- `libzmq-4.3.5/tests/test_spec_req.cpp`
- `libzmq-4.3.5/tests/test_spec_rep.cpp`
- `libzmq-4.3.5/tests/test_reqrep_tcp.cpp`
- `libzmq-4.3.5/tests/test_reqrep_inproc.cpp`
- `libzmq-4.3.5/tests/test_hwm.cpp`
- `libzmq-4.3.5/tests/test_monitor.cpp`

实验结论必须同时包含“外部观察到的行为”和“源码中对应的处理位置”。

## 8. 常见误区

- 试图一次读完整个源码；
- 只读注释，不追踪调用者和错误分支；
- 把某一版本的内部函数名当成稳定 API；
- 只画网络路径，忽略 Pipe 和队列；
- 把 I/O thread 当作业务线程；
- 没有通过测试用例验证自己的理解。

## 9. 本周验收

提交：

- `send` 调用链；
- `recv` 调用链；
- REP 状态机源码笔记；
- Pipe、Session、Engine 关系图；
- 至少一条源码路径和一个测试用例的对应说明。

能够解释：消息从业务线程如何进入 Pipe、Session 和 Engine；REP 为什么按状态限制 recv/send；连接断开时哪个对象负责通知上层。

## 10. 学习记录模板

```markdown
## 第 11 周学习记录

- 学习日期：
- libzmq 版本：
- 阅读路径：
- 入口函数：
- 状态机变化：
- Pipe/Session/Engine 关系：
- 对照测试：
- 运行验证：
- 与文档一致之处：
- 仍不理解的问题：
```

## 11. 延伸阅读

- `libzmq-4.3.5/src/socket_base.cpp`
- `libzmq-4.3.5/src/req.cpp`
- `libzmq-4.3.5/src/rep.cpp`
- `libzmq-4.3.5/src/session_base.cpp`
- 第 12 周资料：[生产化 RPC 综合项目](第12周学习资料-生产化RPC综合项目.md)
