package com.timmy.NIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO网络访问实现：客户端
 * 核心对象：
 * 1。Channel -- 通道，数据传输使用
 * 2。Selector -- 复用器，多路复用
 * 3.Buffer - ByteBuffer 缓存
 * -客户端使用SocketChannel，并调用connect方法与服务端进行链接
 * -绑定复用器selector，并进行监听
 */
public class Client {

    private final Selector selector;
    private ByteBuffer receivBuffer = ByteBuffer.allocateDirect(1024);
    private ByteBuffer sendBuffer = ByteBuffer.allocateDirect(1024);

    public static void main(String[] args) throws IOException {
        final Client server = new Client();
        //在线程中处理
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                server.receiveFromUser();
            }
        });
        thread.start();

        server.action();
    }

    private void receiveFromUser() {
        //获取控制台输入
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String msg;
            while ((msg = bufferedReader.readLine()) != null) {
                synchronized (sendBuffer) {
                    sendBuffer.put((msg + "\r\n").getBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Client() throws IOException {
        //1。准备通道和复用器
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);// 设置通道不阻塞
        //与服务端连接
        channel.connect(new InetSocketAddress(8089));

        //3。复用器，多路复用
        selector = Selector.open();
        //3。1。channel通道注册到复用器上，这样复用器才能监听所有channel
        channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
    }

    private void action() throws IOException {
        // 4。判断复用器有多少个链接在里面
        while (selector.select() > 0) {
            //获取所有SelectionKey，并判断状态
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();

                //客户端发送过来的数据，SelectionKey可读可写
                if (selectionKey.isReadable()) {
                    System.out.println("--isReadable");
                    SocketChannel channel = (SocketChannel) selectionKey.channel();

                    //数据读取
                    channel.read(receivBuffer);
                    receivBuffer.flip();
                    String receiveData = Charset.forName("UTF-8").decode(receivBuffer).toString();
                    System.out.println("client: get msg:" + receiveData);
                    receivBuffer.clear();
                }

                //写
                if (selectionKey.isWritable()) {
                    System.out.println("--isWritable");
                    SocketChannel channel = (SocketChannel) selectionKey.channel();

                    synchronized ((sendBuffer)) {
                        sendBuffer.flip();
                        while (sendBuffer.hasRemaining()) {
                            channel.write(sendBuffer);
                        }
                        sendBuffer.compact();
                    }
                }
            }
        }
    }
}
