package com.timmy.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO网络访问实现：服务端
 * 核心对象：
 * 1。Channel -- 通道，数据传输使用
 * 2。Selector -- 复用器，多路复用
 * 3.Buffer - ByteBuffer 缓存
 * - 从channel中创建点对点通信的socket，
 * - 将channel注册到selector上，复用器就可以监听通道中所有请求的状态
 */
public class Server {

    private final Selector selector;
    private ByteBuffer readBuffer = ByteBuffer.allocateDirect(1024);
    private ByteBuffer writeBuffer = ByteBuffer.allocateDirect(1024);

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.action();
    }

    public Server() throws IOException {
        //1。准备通道和复用器
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);// 设置通道不阻塞
        //2。获取通道的读写链接socket
        ServerSocket serverSocket = serverSocketChannel.socket();
        //绑定，监听端口
        serverSocket.bind(new InetSocketAddress(38089));

        //3。复用器，多路复用
        selector = Selector.open();

        //3。1。channel通道注册到复用器上，这样复用器才能监听所有channel
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    private void action() throws IOException {
        System.out.println("serve action");
        // 4。判断复用器有多少个链接在里面
        while (selector.select() > 0) {
            System.out.println("selector.select() > 0 ");
            //获取所有SelectionKey，并判断状态
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();

                //新的链接
                if (selectionKey.isAcceptable()) {
                    System.out.println("--isAcceptable");
                    ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();

                    //获取channel中的 socket
                    SocketChannel socketChannel = channel.accept();
                    if (socketChannel == null) {
                        continue;
                    }
                    //继续监听
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

                    //写输入到channel中
                    //申请堆外内容
                    ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
                    byteBuffer.put("hello i am server".getBytes());
                    byteBuffer.flip();

                    socketChannel.write(byteBuffer);
                }

                //客户端发送过来的数据，SelectionKey可读可写
                if (selectionKey.isReadable()) {
                    System.out.println("--isReadable");
                    SocketChannel channel = (SocketChannel) selectionKey.channel();

                    //数据读取
                    readBuffer.clear();
                    channel.read(readBuffer);
                    readBuffer.flip();

                    //
                    String receiveData = Charset.forName("UTF-8").decode(readBuffer).toString();
                    System.out.println("server get msg:" + receiveData);

                    //打读到的数据绑定到key中
                    selectionKey.attach("server msg echo:" + receiveData);
                }

                //写
                if (selectionKey.isWritable()) {
                    System.out.println("--isWritable");
                    SocketChannel channel = (SocketChannel) selectionKey.channel();

                    String message = (String) selectionKey.attachment();
                    if (message == null) {
                        continue;
                    }

                    selectionKey.attach(null);

                    writeBuffer.clear();
                    writeBuffer.put(message.getBytes());
                    writeBuffer.flip();
                    while (writeBuffer.hasRemaining()) {
                        channel.write(writeBuffer);
                    }
                }
            }
        }
    }
}
