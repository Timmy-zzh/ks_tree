#include "zmq.h"
#include <iostream>
#include <zmq.hpp>

int main()
{
    std::cout << " lib zmq client " << std::endl;

    zmq::context_t ctx(1); // 设置为一个线程数

    // 创建应答套接字
    zmq::socket_t sock(ctx, ZMQ_REQ);
    sock.connect("tcp://localhost:5555"); // 连接服务端

    std::string msg = "World";
    zmq::message_t request(msg.data(), msg.size());
    // 发送
    sock.send(request, zmq::send_flags::none);

    // 接收应答
    zmq::message_t reply;
    auto res = sock.recv(reply, zmq::recv_flags::none);
    if (res)
    {
        std::string reqly_str(static_cast<char *>(reply.data()), reply.size());
        std::cout << "reqly_str:" << reqly_str << std::endl;
    }

    return 0;
}