#include "zmq.h"
#include <iostream>
#include <zmq.hpp>
#include <thread>

/**
 * zmq push-pull 推送-拉取模式
 * - 服务端pub发布端
 * - 请求上下文context
 * - 设置socket模式 ZMQ_PUB
 * - bind绑定，监听端口号
 * - 不断发送消息send方法，一次发送多个消息
 * -- 分为 topic 和具体的消息 payload
 */
int main()
{
    std::cout << " lib zmq service" << std::endl;

    // 创建上下文
    zmq::context_t ctx(1); // 设置为一个线程数

    // 创建应答套接字
    zmq::socket_t sock(ctx, ZMQ_PUSH);
    sock.bind("tcp://*:5557"); // 监听所有网卡的5555端口

    std::cout << " servic readed on ip:*:5557" << std::endl;

    while (true)
    {
        static int num = 1;
        std::string data = "push message num:" + std::to_string(num++);
        // 发送消息
        zmq::message_t msg_data(data.data(), data.size());
        sock.send(msg_data, zmq::send_flags::none);

        // 延迟1秒
        std::cout << "push msg: " << data << std::endl;
        std::this_thread::sleep_for(std::chrono::milliseconds(500));
    }

    return 0;
}