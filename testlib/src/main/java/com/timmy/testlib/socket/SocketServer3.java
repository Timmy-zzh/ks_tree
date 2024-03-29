package com.timmy.testlib.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务端io处理放在线程池中处理
 */
class SocketServer3 {

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        int port = 55556;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("----accept---111");
        System.out.println("server,accept zuse");

        while (true) {
            Socket socket = serverSocket.accept();

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream inputStream = socket.getInputStream();
                        byte[] buff = new byte[1024];
                        int len;
                        StringBuilder sb = new StringBuilder();
                        while ((len = inputStream.read(buff)) != -1) {
                            sb.append(new String(buff, 0, len, StandardCharsets.UTF_8));
                        }
                        System.out.println("get message from client:" + sb.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            executorService.submit(runnable);
        }

    }
}
