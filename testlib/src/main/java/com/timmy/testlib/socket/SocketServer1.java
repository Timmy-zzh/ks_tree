package com.timmy.testlib.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

class SocketServer1 {

    public static void main(String[] args) throws IOException {
        int port = 55556;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("----accept---111");
        System.out.println("server,accept zuse");

        Socket socket = serverSocket.accept();
        System.out.println("----accept---222  ");

        InputStream inputStream = socket.getInputStream();
        byte[] buff = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = inputStream.read(buff)) != -1) {
            sb.append(new String(buff, 0, len, StandardCharsets.UTF_8));
        }
        System.out.println("get message from client:" + sb.toString());

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("Hello client ,I am Server,I get message :".getBytes("UTF-8"));

        inputStream.close();
        outputStream.close();

        socket.close();
        serverSocket.close();
    }
}
