# 第 2 周学习资料：REQ/REP 与基线代码

> 本资料配套计划：[第 2 周：REQ/REP 和当前基线代码](第02周-REQ-REP与基线代码.md)。建议先完成第 1 周，再开始本资料。

## 1. 学习目标

完成本周后，你应该能够：

- 独立编译并运行当前 REQ/REP 示例；
- 画出 REQ 和 REP 的状态机；
- 区分 `bind`、`connect`、TCP 建连、`send`、`recv` 和业务完成；
- 解释 ZeroMQ 消息边界、长度和二进制语义；
- 使用超时、日志和多客户端实验定位问题；
- 说明为什么 `send()` 成功不等于服务端业务已经完成。

## 2. REQ/REP 的工作模型

REQ/REP 是最容易入门的请求-应答模式，但它有严格的交替状态：

```text
REQ: send -> recv -> send -> recv -> ...
REP: recv -> send -> recv -> send -> ...
```

REQ 发送请求后才能接收应答；REP 接收请求后才能发送应答。这个约束由 Socket 类型实现，不是注释约定。

```text
客户端 REQ                         服务端 REP
connect -------------------------> bind 的 Endpoint
send(request) -------------------> recv(request)
                                    业务处理
recv(reply) <--------------------- send(reply)
```

REQ/REP 适合简单、同步、一次一问一答的 RPC。它不适合一个客户端同时维护大量未完成请求，也不适合复杂的异步回调；这些场景通常使用 DEALER/ROUTER。

## 3. 事件顺序和含义

以下事件不是同时发生的：

| 事件 | 含义 |
| --- | --- |
| 服务端 `bind` 返回 | 本地 Socket 已占用并监听 Endpoint |
| 客户端 `connect` 返回 | 连接请求已提交给 ZeroMQ，不保证对端已处理 |
| TCP 连接建立 | 底层连接完成，可能还要进行 ZMTP 握手 |
| 服务端 `recv` 返回 | 一条完整消息已交付到 REP Socket |
| 服务端 `send` 返回 | 消息被 ZeroMQ 接受或排入本地队列 |
| 客户端 `recv` 返回 | 客户端收到完整应答 |
| 业务处理完成 | 应用逻辑完成，可能早于或晚于发送应答 |

`send()` 默认主要表示本地 Socket 接受了消息。它不确认对端已经收到，更不确认对端已经完成数据库更新或其他业务操作。

## 4. 阅读当前基线代码

代码位置：

- [service_rep.cpp](../../test_zeromq/service_rep.cpp)
- [client_req.cpp](../../test_zeromq/client_req.cpp)
- [CMakeLists.txt](../../test_zeromq/CMakeLists.txt)

服务端的核心逻辑：

```cpp
zmq::context_t ctx(1);
zmq::socket_t sock(ctx, ZMQ_REP);
sock.bind("tcp://*:5555");

while (true) {
    zmq::message_t request;
    auto result = sock.recv(request, zmq::recv_flags::none);
    if (!result)
        break;

    std::string text(static_cast<char*>(request.data()), request.size());
    std::string reply_text = "Hello " + text;
    zmq::message_t reply(reply_text.data(), reply_text.size());
    sock.send(reply, zmq::send_flags::none);
}
```

客户端的核心逻辑：

```cpp
zmq::context_t ctx(1);
zmq::socket_t sock(ctx, ZMQ_REQ);
sock.connect("tcp://localhost:5555");

zmq::message_t request("World", 5);
sock.send(request, zmq::send_flags::none);

zmq::message_t reply;
sock.recv(reply, zmq::recv_flags::none);
```

注意 `message_t` 是带长度的字节块，接收时使用 `reply.size()`，不要假定消息以 `\0` 结尾。

## 5. 编译和运行

```bash
cd /home/zhuzhonghua/zzh/github/ks_tree/zmq/test_zeromq
cmake -S . -B build
cmake --build build -j"$(nproc)"
```

终端 A：

```bash
./build/service
```

终端 B：

```bash
./build/client
```

预期结果：服务端打印 `received:World`，客户端打印 `Hello World`。如果动态库不在系统搜索路径中：

```bash
export LD_LIBRARY_PATH=/home/zhuzhonghua/zzh/github/ks_tree/zmq/cmake_libzmq/lib:$LD_LIBRARY_PATH
```

当前 CMake 生成的目标叫 `service` 和 `client`，不是 `service_rep` 和 `client_req`。

## 6. 实验一：消息边界

将客户端请求改为包含空字节的内容：

```cpp
std::string payload("ab\0cd", 5);
zmq::message_t request(payload.data(), payload.size());
```

服务端应按 `request.size()` 读取 5 个字节。如果使用 `strlen()` 或直接当 C 字符串输出，通常只能看到 `ab`，这不是 ZeroMQ 丢失了数据，而是 C 字符串遇到 `\0` 提前结束。

再测试空消息：

```cpp
zmq::message_t request;
sock.send(request, zmq::send_flags::none);
```

记录服务端收到的消息大小和回复行为。

## 7. 实验二：状态机错误

尝试让 REQ 连续发送两次而不接收回复，或让 REP 连续接收两次而不发送回复。观察调用是否阻塞、返回错误或进入不可继续的状态。

结论：

- REQ 需要先完成 `send -> recv` 才能发下一条；
- REP 需要先完成 `recv -> send` 才能接收下一条；
- 如果业务需要多个并发未完成请求，改用 DEALER/ROUTER 或建立多个 REQ Socket。

## 8. 实验三：多客户端和生命周期

同时启动多个 `build/client`，观察 REP 服务端如何逐个处理请求。然后分别测试：

1. 客户端连接后不发送；
2. 客户端发送后立即退出；
3. 服务端处理前增加延迟；
4. 服务端按 `Ctrl+C` 退出，再重启；
5. 服务端退出期间启动客户端，稍后再启动服务端。

记录以下字段：

| 场景 | 客户端现象 | 服务端现象 | 是否自动恢复 | 原因 |
| --- | --- | --- | --- | --- |
| 客户端不发送 | | | | |
| 客户端提前退出 | | | | |
| 服务端重启 | | | | |
| 多客户端 | | | | |

不要把“连接已建立”与“业务一定成功”混为一谈。

## 9. 常见问题

### 客户端一直阻塞

确认服务端已经运行、两边端口一致、服务端没有卡在错误的 REP 状态，并检查 `ss -ltnp | rg ':5555'`。

### `Address already in use`

检查旧服务端：

```bash
ss -ltnp | rg ':5555'
ps -ef | rg 'build/service'
```

只结束确认无用的旧进程，或同步修改两边端口。

### 启动时找不到 `libzmq.so`

使用 `ldd build/client | rg 'zmq|not found'` 查看运行时依赖，临时设置 `LD_LIBRARY_PATH` 后再运行。

### 修改代码后行为没有变化

确认重新执行了 `cmake --build build`，并运行的是当前目录下的 `build/service` 与 `build/client`。

## 10. 练习和参考答案

### 练习

1. 为客户端增加接收超时，并说明超时后 REQ Socket 是否可以直接继续发送。
2. 绘制从 `bind` 到客户端 `recv` 的时序图。
3. 解释为什么服务端 `send` 返回后，客户端可能还没有收到消息。
4. 设计一个允许多个并发请求的改造方案。

### 参考答案要点

1. 超时后的 REQ 可能仍处在等待应答的状态，不能简单地继续发送；可以关闭并重建 Socket，或使用 DEALER 设计自己的请求状态机。
2. 时序至少包含 bind、connect、底层连接、服务端 recv、业务处理、服务端 send、客户端 recv。
3. ZeroMQ 有本地队列和底层缓冲，发送返回只说明本地接受，不是端到端确认。
4. 使用多个 REQ Socket，或改用 DEALER/ROUTER 并在应用层加入 request ID 和响应匹配。

## 11. 本周验收

不看资料回答：

- REQ 和 REP 的收发顺序是什么？
- `message_t` 为什么不能默认当 C 字符串？
- `connect()` 返回是否表示业务连接成功？
- `send()` 成功是否表示业务处理完成？
- 为什么客户端重试可能导致重复业务？
- 当前工程的两个可执行文件叫什么？

完成基线运行、消息边界实验和至少两种异常场景后，再进入第 3 周。

## 12. 学习记录模板

```markdown
## 第 2 周学习记录

- 学习日期：
- 编译命令和结果：
- 正常请求结果：
- 空消息结果：
- 二进制消息结果：
- 状态机错误现象：
- 多客户端结果：
- 我理解的 send 语义：
- 我理解的 recv 语义：
- 遇到的问题和排查过程：
- 未解决问题：
```

## 13. 延伸阅读

- `libzmq-4.3.5/doc/zmq_send.txt`
- `libzmq-4.3.5/doc/zmq_recv.txt`
- `libzmq-4.3.5/tests/test_spec_req.cpp`
- `libzmq-4.3.5/tests/test_spec_rep.cpp`
- 第 3 周资料：[消息帧、多段消息与序列化](第03周学习资料-消息帧多段消息与序列化.md)
