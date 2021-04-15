package com.timmy.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

class SocketClient2 {

    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int port = 55556;
        System.out.println("client socket start 1111 ");
        Socket socket = new Socket(host, port);
        System.out.println("client socket start");

        // 客户端Socket复用，多次向服务端发送请求
        OutputStream outputStream = socket.getOutputStream();
        String message = "first -- hello server";
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);

        //先发送消息的长度
        outputStream.write(bytes.length >> 8);
        outputStream.write(bytes.length);
        //发送消息内容
        outputStream.write(message.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();

        //------------第二条消息-----------------
         message = "second - message";
        bytes = message.getBytes(StandardCharsets.UTF_8);
        outputStream.write(bytes.length >> 8);
        outputStream.write(bytes.length);
        outputStream.write(message.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();

        //------------第三条消息-----------------
         message = "three - message";
        bytes = message.getBytes(StandardCharsets.UTF_8);
        outputStream.write(bytes.length >> 8);
        outputStream.write(bytes.length);
        outputStream.write(message.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();

        outputStream.close();
        socket.close();

    }
}
