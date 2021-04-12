package com.timmy.testlib.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

class SocketClient1 {

    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int port = 55556;
        System.out.println("client socket start 1111 ");
        Socket socket = new Socket(host, port);
        System.out.println("client socket start");

        OutputStream outputStream = socket.getOutputStream();
        String message = "I am client,request connet -- hello server";
        outputStream.write(message.getBytes(StandardCharsets.UTF_8));

//        socket.shutdownOutput();

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
