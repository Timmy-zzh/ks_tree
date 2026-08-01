#include "zmq.h"
#include <iostream>
#include <zmq.hpp>

/**
 * zmq pub-sub 发布订阅模式
 * 订阅客服端
 * - 不断接受订阅消息
 */
int main()
{
    std::cout << " lib zmq client " << std::endl;

    zmq::context_t ctx(1); // 设置为一个线程数
    // 创建应答套接字
    zmq::socket_t sock(ctx, ZMQ_SUB);
    sock.connect("tcp://localhost:5556"); // 连接服务端

    // 设置订阅的主题
    sock.set(zmq::sockopt::subscribe, "v1/timmy");
    // 也可以订阅多个主题，接收多个消息

    std::cout << "subscriber connected , waiting for message ... " << std::endl;

    while (true)
    {
        zmq::message_t msg_topic;
        zmq::message_t msg_data;
        auto res_topic = sock.recv(msg_topic, zmq::recv_flags::none);
        auto res_data = sock.recv(msg_data, zmq::recv_flags::none);

        if (res_topic && res_data)
        {
            std::string topic(static_cast<char *>(msg_topic.data()), msg_topic.size());
            std::string data(static_cast<char *>(msg_data.data()), msg_data.size());
            std::cout << "Received:" << topic << " - " << data << std::endl;
        }
    }

    return 0;
}