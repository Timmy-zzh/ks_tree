package com.timmy.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Socket编程：服务端1
 * <p>
 * -使用ServerSocket创建socket通信，
 * -调用accept方法，阻塞，等待客户端入境请求接入
 * -如果又客户端请求连接了，则调用getIntputStream方法获取客户端发送过来的请求
 * -打印输出后，并向客户端发送数据，调用write方法
 */
class SocketServer1 {

    public static void main(String[] args) throws IOException {
        //1.创建服务端Socket，传入port端口号
        int port = 55556;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("----accept---111");
        System.out.println("服务端，阻塞等待客户端连接请求的到来");
        //2。accept方法是阻塞方法
        Socket socket = serverSocket.accept();
        System.out.println("----accept---222  ");

        //3。有入境请求接入后，会执行到下面方法
        InputStream inputStream = socket.getInputStream();
        byte[] buff = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        //4。不断读取数据，并保存在缓存buff中，使用StringBuilder进行缓存数据的保存
        while ((len = inputStream.read(buff)) != -1) {
            sb.append(new String(buff, 0, len, StandardCharsets.UTF_8));
        }
        System.out.println("get message from client:" + sb.toString());

        //5。给客户端发送数据
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("Hello client ,I am Server,I get message :".getBytes("UTF-8"));

        inputStream.close();
        outputStream.close();

        socket.close();
        serverSocket.close();
    }
}
