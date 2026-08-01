#include "zmq.h"
#include <iostream>
#include <zmq.hpp>

/**
 * zmq router-dealer 异步请求应答模式
 * - 服务端router
 * - 接收消息，并回复消息
 */
int main()
{
    std::cout << " lib zmq service" << std::endl;

    // 创建上下文
    zmq::context_t ctx(1); // 设置为一个线程数

    // 创建应答套接字
    zmq::socket_t router(ctx, ZMQ_ROUTER);
    router.bind("tcp://*:5558"); // 监听所有网卡的5555端口

    std::cout << " servic readed on ip:*:5558" << std::endl;

    // 接收到立刻就回复消息。 那他是如何保证异步操作呢？？
    while (true)
    {
        zmq::message_t identity; // 对方身份信息
        // zmq::message_t empty;    // 空消息
        zmq::message_t content; // 消息体

        // 等待接收消息（阻塞）
        auto res = router.recv(identity, zmq::recv_flags::none);
        // router.recv(empty, zmq::recv_flags::none);
        router.recv(content, zmq::recv_flags::none);

        // 打印收到的消息
        std::string client_id(static_cast<char *>(identity.data()), identity.size());
        std::string request_str(static_cast<char *>(content.data()), content.size());
        std::cout << "received from:" << client_id << " - " << request_str << std::endl;

        // 回复消息
        // 构造应答消息
        std::string reply_str = "Ack " + request_str;
        zmq::message_t reply(reply_str.data(), reply_str.size());

        // 发送
        router.send(identity, zmq::send_flags::sndmore);
        // router.send(empty, zmq::send_flags::sndmore);
        router.send(reply, zmq::send_flags::none);
    }

    return 0;
}