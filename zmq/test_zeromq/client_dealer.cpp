#include "zmq.h"
#include <iostream>
#include <zmq.hpp>

int main()
{
    std::cout << " lib zmq client " << std::endl;

    zmq::context_t ctx(1); // 设置为一个线程数

    // 创建应答套接字
    zmq::socket_t sock(ctx, ZMQ_DEALER);
    sock.set(zmq::sockopt::routing_id, "clinet-1"); // 设置客户端身份
    sock.connect("tcp://localhost:5558");           // 连接服务端

    // 需要设置身份，不设置的话，会自动生成一个

    // 模拟发送消息
    for (int i = 0; i < 5; i++)
    {
        std::string msg = "Hello " + std::to_string(i);
        zmq::message_t request(msg.data(), msg.size());
        // 发送
        sock.send(request, zmq::send_flags::none);

        std::cout << "send msg:" << msg << std::endl;
    }

    // 接收应答
    for (int i = 0; i < 5; i++)
    {
        zmq::message_t reply;
        auto res = sock.recv(reply, zmq::recv_flags::none);
        if (res)
        {
            std::string reqly_str(static_cast<char *>(reply.data()), reply.size());
            std::cout << "Receive msg:" << reqly_str << std::endl;
        }
    }

    return 0;
}