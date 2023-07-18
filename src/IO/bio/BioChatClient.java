package IO.bio;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class BioChatClient {
    private Socket clientSocket;
    private PrintWriter writer;

    public void start(String host, int port) throws IOException {
        clientSocket = new Socket(host, port);
        System.out.println("Connected to server: " + host + ":" + port);

        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        writer = new PrintWriter(clientSocket.getOutputStream(), true);

        // 启动读线程
        Thread readThread = new Thread(this::readFromServer);
        readThread.start();

        // 发送消息
        sendMessages();
    }

    private void readFromServer() {
        try {
            String message;
            BufferedReader reader = null;
            while ((message = reader.readLine()) != null) {
                System.out.println("Received message from server: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessages() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter a message (or 'quit' to exit): ");
            String message = scanner.nextLine();

            if (message.equalsIgnoreCase("quit")) {
                break;
            }

            writer.println(message);
        }

        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        BioChatClient client = new BioChatClient();
        client.start("localhost", 8080);
    }
}
