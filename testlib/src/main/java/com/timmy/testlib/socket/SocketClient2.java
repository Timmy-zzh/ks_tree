package com.timmy.testlib.socket;

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

        OutputStream outputStream = socket.getOutputStream();
        String message = "first -- hello server";
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);

        outputStream.write(bytes.length >> 8);
        outputStream.write(bytes.length);
        outputStream.write(bytes);
        outputStream.flush();

        message = "second - message";
        bytes = message.getBytes(StandardCharsets.UTF_8);
        outputStream.write(bytes.length >> 8);
        outputStream.write(bytes.length);
        outputStream.write(bytes);
        outputStream.flush();

        message = "three - message";
        bytes = message.getBytes(StandardCharsets.UTF_8);
        outputStream.write(bytes.length >> 8);
        outputStream.write(bytes.length);
        outputStream.write(bytes);

        outputStream.close();
        socket.close();
    }
}
