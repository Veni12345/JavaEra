package IO.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * 基于NIO的连接
 */
public class NIOChatServer {
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

                keyIterator.remove();
            }
        }
    }

    private void handleConnection(SelectionKey key) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        SocketChannel clientChannel = serverChannel.accept();
        clientChannel.configureBlocking(false);

        // 注册读事件到Selector上
        clientChannel.register(selector, SelectionKey.OP_READ);

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

        // 将数据广播给所有连接的客户端
        broadcast(data, clientChannel);
    }

    private void broadcast(byte[] data, SocketChannel excludeChannel) throws IOException {
        for (SelectionKey key : selector.keys()) {
            Channel channel = key.channel();
            if (channel instanceof SocketChannel && channel != excludeChannel) {
                SocketChannel clientChannel = (SocketChannel) channel;
                clientChannel.write(ByteBuffer.wrap(data));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        NIOChatServer server = new NIOChatServer();
        server.start(8080);
    }
}

