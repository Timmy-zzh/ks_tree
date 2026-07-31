#include "zmq.h"
#include <iostream>
#include <zmq.hpp>

/**
 * zmq 请求-应答模式代码实践
 * - 先实现服务端
 * - 类： 上下文，套接字，消息体
 * - 逻辑：
 * -- 创建进程上下文，和套接字
 * -- 套接字socket监听，当前设备，和端口5555
 * -- 使用while循环等待客户端接入，
 * -- 读取客户端发送过来的data消息，输出来
 * -- 接着给客户端回复消息
 */
int main()
{
    std::cout << " lib zmq service" << std::endl;

    // 创建上下文
    zmq::context_t ctx(1); // 设置为一个线程数

    // 创建应答套接字
    zmq::socket_t sock(ctx, ZMQ_REP);
    sock.bind("tcp://*:5555"); // 监听所有网卡的5555端口

    std::cout << " servic readed on ip:*:5555" << std::endl;

    while (true)
    {

        zmq::message_t request;

        // 等待接收消息（阻塞）
        auto res = sock.recv(request, zmq::recv_flags::none);
        if (!res)
        { // 连接关闭操作
            break;
        }

        // 打印接收到的消息
        std::string req_str(static_cast<char *>(request.data()), request.size());
        std::cout << "received:" << req_str << std::endl;

        // 构造应答消息
        std::string reply_str = "Hello " + req_str;
        zmq::message_t reply(reply_str.data(), reply_str.size());

        // 发送
        sock.send(reply, zmq::send_flags::none);
    }

    return 0;
}