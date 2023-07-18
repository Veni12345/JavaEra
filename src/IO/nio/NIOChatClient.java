package IO.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class NIOChatClient {
    private SocketChannel clientChannel;
    private ByteBuffer buffer = ByteBuffer.allocate(1024);

    public void start(String host, int port) throws IOException {
        // 连接服务器
        clientChannel = SocketChannel.open();
        clientChannel.configureBlocking(false);
        clientChannel.connect(new InetSocketAddress(host, port));

        // 等待连接完成
        while (!clientChannel.finishConnect()) {
            // 可以做一些其他操作
        }

        System.out.println("Connected to server: " + host + ":" + port);

        // 启动读线程
        Thread readThread = new Thread(this::readFromServer);
        readThread.start();

        // 发送消息
        sendMessages();
    }

    private void readFromServer() {
        try {
            while (true) {
                buffer.clear();
                int bytesRead = clientChannel.read(buffer);
                if (bytesRead <= 0) {
                    // 连接关闭
                    clientChannel.close();
                    break;
                }

                buffer.flip();
                byte[] data = new byte[buffer.limit()];
                buffer.get(data);
                String message = new String(data);
                System.out.println("Received message from server: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessages() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter a message (or 'quit' to exit): ");
            String message = scanner.nextLine();

            if (message.equalsIgnoreCase("quit")) {
                break;
            }

            buffer.clear();
            buffer.put(message.getBytes());
            buffer.flip();
            clientChannel.write(buffer);
        }

        clientChannel.close();
    }

    public static void main(String[] args) throws IOException {
        NIOChatClient client = new NIOChatClient();
        client.start("localhost", 8080);
    }
}
