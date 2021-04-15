package com.timmy.testlib.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

class SocketServer2 {

    public static void main(String[] args) throws IOException {
        int port = 55556;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("----accept---111");
        System.out.println("server,accept zuse");

        Socket socket;
        InputStream inputStream;
        byte[] buff;

        socket = serverSocket.accept();
        System.out.println("----accept---222  ");
        inputStream = socket.getInputStream();

        while (true) {
            int first = inputStream.read();
            if (first == -1) {       //elf - finish
                break;
            }
            // read continue
            int second = inputStream.read();
            //read len
            int len = (first << 8) + second;
            buff = new byte[len];
            inputStream.read(buff);
            System.out.println("i am server,get msg:" + new String(buff, StandardCharsets.UTF_8));
        }

        inputStream.close();

        socket.close();
        serverSocket.close();
    }
}
