package IO.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 网络服务器 非阻塞式IO模型
 * <p>
 * 它使用ServerSocketChannel监听指定端口，通过Selector实现多路复用机制。
 * 在事件循环中，它会处理已就绪的事件，包括接受客户端连接请求和处理读事件。
 * 通过这种方式，服务器可以同时处理多个客户端连接，并且不需要为每个连接创建一个新的线程，提高了服务器的并发性能。
 */
public class NonBlockingServer {
    private ServerSocketChannel serverChannel;
    private Selector selector;
    private ByteBuffer buffer = ByteBuffer.allocate(1024);

    public void start(int port) throws IOException {
        // 创建ServerSocketChannel并绑定到指定端口
        serverChannel = ServerSocketChannel.open();
        serverChannel.socket().bind(new InetSocketAddress(port));
        serverChannel.configureBlocking(false);

        // 创建Selector并将ServerSocketChannel注册到Selector上
        selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("Server started on port " + port);

        // 事件循环
        while (true) {
            int readyChannels = selector.select();
            if (readyChannels == 0) {
                continue;
            }

            // 处理已就绪的事件
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();

                if (key.isAcceptable()) {
                    // 处理客户端连接请求
                    handleConnection(key);
                }

                if (key.isReadable()) {
                    // 处理读事件
                    handleRead(key);
                }

                if (key.isWritable()) {
                    // 处理写事件
                    handleWrite(key);
                }


                keyIterator.remove();
            }
        }
    }

    private void handleConnection(SelectionKey key) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        SocketChannel clientChannel = serverChannel.accept();
        clientChannel.configureBlocking(false);

        // 注册读事件到Selector上
        clientChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

        System.out.println("New client connected: " + clientChannel.getRemoteAddress());
    }

    private void handleRead(SelectionKey key) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        buffer.clear();

        int bytesRead = clientChannel.read(buffer);
        if (bytesRead == -1) {
            // 连接关闭
            clientChannel.close();
            key.cancel();
            System.out.println("Client disconnected: " + clientChannel.getRemoteAddress());
            return;
        }

        // 对接收到的数据进行处理（此处简单打印）
        buffer.flip();
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);
        System.out.println("Received data from client " + clientChannel.getRemoteAddress() + ": " + new String(data));
    }

    private void handleWrite(SelectionKey key) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();

        // 发送数据给客户端
        String message = "Hello, client!";
        buffer.clear();
        buffer.put(message.getBytes());
        buffer.flip();
        clientChannel.write(buffer);
    }

    public static void main(String[] args) throws IOException {
        NonBlockingServer server = new NonBlockingServer();
        server.start(8080);
    }
}
