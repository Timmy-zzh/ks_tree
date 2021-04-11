package com.timmy.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Socket客户端1
 * -创建Socket客户端，并指定本机地址
 * --localhost等于127.0.0.1，不过localhost是域名，127.0.0.1是IP地址。
 * --localhost和127.0.0.1不需要联网，都是本机访问。
 * -通过输出流往服务端写入数据
 * -并获取服务端返回的数据
 */
class SocketClient1 {

    public static void main(String[] args) throws IOException {
        //1。客户端创建socket连接
        String host = "127.0.0.1";
        int port = 55556;
        System.out.println("client socket start 1111 ");
        Socket socket = new Socket(host, port);
        System.out.println("client socket start");

        //2。往服务端写数据
        OutputStream outputStream = socket.getOutputStream();
        String message = "我是客户端1，请求连接 -- hello server";
        outputStream.write(message.getBytes(StandardCharsets.UTF_8));

//        socket.shutdownOutput();

        //3。获取服务端返回的数据
        InputStream inputStream = socket.getInputStream();
        byte[] buff = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = inputStream.read(buff)) != -1) {
            sb.append(new String(buff, 0, len, StandardCharsets.UTF_8));
        }
        System.out.println("Client-  get message from server:" + sb.toString());

        inputStream.close();
        outputStream.close();
        socket.close();

    }
}
