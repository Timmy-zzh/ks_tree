#include "zmq.h"
#include <iostream>
#include <zmq.hpp>

/**
 * zmq push-pull
 * 拉取客服端
 * - 可以启动多个
 */
int main()
{
    std::cout << " lib zmq client " << std::endl;

    zmq::context_t ctx(1); // 设置为一个线程数
    // 创建应答套接字
    zmq::socket_t sock(ctx, ZMQ_PULL);
    sock.connect("tcp://localhost:5557"); // 连接服务端

    std::cout << "client connected , waiting for message ... " << std::endl;

    while (true)
    {
        zmq::message_t msg_data;
        auto res_data = sock.recv(msg_data, zmq::recv_flags::none);

        if (res_data)
        {
            std::string data(static_cast<char *>(msg_data.data()), msg_data.size());
            sleep(1);
            std::cout << "Received:" << data << std::endl;
        }
    }

    return 0;
}