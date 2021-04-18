package com.timmy.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * 服务端：
 */
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel channel = ServerSocketChannel.open();

        ServerSocket socket = channel.socket();
        //绑定，监听端口
        channel.bind(new InetSocketAddress(8089));

        //复用器，多路复用
        Selector selector = Selector.open();
    }
}
