# 第 11 周：libzmq 源码阅读

## 学习目标

沿着发送和接收路径理解内部架构，不追求一次读完整个源码。

## 推荐阅读顺序

### 公共 API 和 Context

- `zmq/libzmq-4.3.5/include/zmq.h`；
- `zmq/libzmq-4.3.5/src/ctx.cpp`；
- `zmq/libzmq-4.3.5/src/socket_base.cpp`；
- `zmq/libzmq-4.3.5/src/zmq.cpp`。

### REQ/REP 状态机

- `zmq/libzmq-4.3.5/src/req.cpp`；
- `zmq/libzmq-4.3.5/src/rep.cpp`；
- `zmq/libzmq-4.3.5/src/dealer.cpp`；
- `zmq/libzmq-4.3.5/src/router.cpp`。

### 内部线程和 Pipe

- `zmq/libzmq-4.3.5/src/io_thread.cpp`；
- `zmq/libzmq-4.3.5/src/pipe.cpp`；
- `zmq/libzmq-4.3.5/src/session_base.cpp`。

### TCP 和 ZMTP

- `zmq/libzmq-4.3.5/src/tcp.cpp`；
- `zmq/libzmq-4.3.5/src/tcp_engine.cpp`；
- `zmq/libzmq-4.3.5/src/zmtp_engine.cpp`；
- `zmq/libzmq-4.3.5/src/options.cpp`。

## 发送路径

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

## 接收路径

```text
TCP
  -> engine
  -> session
  -> pipe
  -> Socket 接收队列
  -> zmq_recv
  -> 业务线程
```

## 源码阅读记录要求

每次只读一条调用路径，并记录：

- 入口函数；
- 当前线程；
- 消息对象位置；
- 状态机变化；
- 队列变化；
- 错误分支；
- 连接断开分支；
- 与文档描述的对应关系。

## 本周输出

- `send` 调用链；
- `recv` 调用链；
- REP 状态机源码笔记；
- Pipe、Session、Engine 关系图。
