package IO.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NonBlockingClient {
    private static final int NUM_CLIENTS = 100; // 客户端数量

    public void start() throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_CLIENTS);

        for (int i = 0; i < NUM_CLIENTS; i++) {
            executor.submit(new ClientThread());
        }

        executor.shutdown();
    }

    private class ClientThread implements Runnable {
        private ByteBuffer buffer = ByteBuffer.allocate(1024);

        @Override
        public void run() {
            try {
                SocketChannel clientChannel = SocketChannel.open();
                clientChannel.configureBlocking(false);  //SocketChannel非阻塞模式
                clientChannel.connect(new InetSocketAddress("localhost", 8080));

                while (!clientChannel.finishConnect()) {
                    // 等待连接成功
                }

                // 发送数据给服务器
                String message = "Hello, server!";
                buffer.clear();
                buffer.put(message.getBytes());
                buffer.flip();
                clientChannel.write(buffer);

                buffer.clear();
                clientChannel.read(buffer);
                buffer.flip();

                byte[] data = new byte[buffer.limit()];
                buffer.get(data);
                System.out.println("Received response from server: " + new String(data));

                clientChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        NonBlockingClient client = new NonBlockingClient();
        client.start();
    }
}

