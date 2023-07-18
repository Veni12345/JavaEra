package IO.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用BIO（Blocking I/O，阻塞IO）模型的实际应用案例是简单的网络聊天室
 */
public class BioChatServer {
    private ServerSocket serverSocket;
    private List<Socket> clients = new ArrayList<>();

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("New client connected: " + clientSocket.getRemoteSocketAddress());

            clients.add(clientSocket);

            Thread clientThread = new Thread(() -> {
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                    String message;
                    while ((message = reader.readLine()) != null) {
                        System.out.println("Received message from " + clientSocket.getRemoteSocketAddress() + ": " + message);

                        broadcastMessage(message, clientSocket);
                    }

                    System.out.println("Client disconnected: " + clientSocket.getRemoteSocketAddress());
                    clients.remove(clientSocket);
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            clientThread.start();
        }
    }

    private void broadcastMessage(String message, Socket sender) throws IOException {
        for (Socket client : clients) {
            if (client != sender) {
                PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
                writer.println(message);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BioChatServer server = new BioChatServer();
        server.start(8080);
    }
}
