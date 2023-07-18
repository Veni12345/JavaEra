package IO.socket;

import java.io.*;
import java.net.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

/*
* Socket是TCP/IP中的基本概念，它负责将TCP/IP包发送到指定的IP地址。也可以看成是在两个程序进行通信连接中的一个端点，
* 一个程序将 信息写入Socket中(类似于插座)，该Socket将这段信息发送到另一个Socket中(类似于插头)，
* 使这段信息能够传送到其他程序。这两端的程序可以是在一台计算机 上,也可以在因特网的远程计算机上。

当两个程序需要通信时，可以使用Socket类建立套接字连接。呼叫的一方称为客户机,负责监听的一方称为服务器。
* 由于TCP/IP协议是基于连接的、可靠的协议，所以客户机服务器可以在这条连接上可靠地传输数据。
* 服务器所用的套接字是ServerSocket, 客户机所用的套接字是Socket。一个Socket由一个IP地址和一 个端口号唯一确定 。
*
* 在传统的UNIX环境下可以操作TCP/IP协议的接口不止Socket一个，Socket 所支持的协议种类也不光TCP/IP -种，
* 因此两者之间是没有必然联系的。在Java环境下，Socket编程主要是指基于TCP/IP协议的网络编程。
 */

/**
 * 使用Socket 进行Client/Server程序设计的一般连接过程 是这样的: Server 端Listen (监听)某个端口是否有连接请求，Client 端向Server端发出Connect (连接)请求，Server 端向Client端发回Accept (接收)消息。-个连接就建 立起来了。Server 端和Client端都可以通过Send和Write等方法与对方通信。
 * 对于一个功能齐全的Socket,其工作过程包含以下4个基本的步骤。
 * <p>
 * 创建Socket
 * 打开连接到Socket的输入/输出流。
 * 按照一定的协议对Socket进行读写操作。
 * 关闭Socket。
 */
public class ChatServer implements ActionListener, Runnable {

    JTextArea showArea;
    JTextField msgText;
    JFrame mainJframe;
    JButton sentBtn;
    JScrollPane JSPane;
    JPanel pane;
    Container con;
    Thread thread = null;
    ServerSocket serverSocket;
    Socket connectToClient;
    DataInputStream inFromClient;
    DataOutputStream outToClient;

    public ChatServer() {
        //设置界面
        mainJframe = new JFrame("聊天——服务端");
        con = mainJframe.getContentPane();
        showArea = new JTextArea();
        showArea.setEditable(false);
        showArea.setLineWrap(true);
        JSPane = new JScrollPane(showArea);
        msgText = new JTextField();
        msgText.setColumns(30);
        msgText.addActionListener(this);
        sentBtn = new JButton("发送");
        sentBtn.addActionListener(this);
        pane = new JPanel();
        pane.setLayout(new FlowLayout());
        pane.add(msgText);
        pane.add(sentBtn);
        con.add(JSPane, BorderLayout.CENTER);
        con.add(pane, BorderLayout.SOUTH);
        mainJframe.setSize(500, 400);
        mainJframe.setVisible(true);
        mainJframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ;
        try {
            //创建服务套接字
            serverSocket = new ServerSocket(5500);
            showArea.append("正在等待对话请求...\n");//侦听客户端的连接
            connectToClient = serverSocket.accept();
            inFromClient = new DataInputStream(connectToClient.getInputStream());
            outToClient = new DataOutputStream(connectToClient.getOutputStream());
            //启动线程在后台来接收对方的消息
            thread = new Thread(this);
            thread.setPriority(Thread.MIN_PRIORITY);
            thread.start();
        } catch (IOException e) {
            showArea.append("对不起，不能创建服务器\n");
            msgText.setEditable(false);
            sentBtn.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        new ChatServer();
    }

    @Override
    //响应按钮事件，发送消息给对方
    public void actionPerformed(ActionEvent e) {
        String s = msgText.getText();
        if (s.length() > 0) {
            try {
                outToClient.writeUTF(s);
                outToClient.flush();
                showArea.append("我（服务端）说：" + msgText.getText() + "\n");
                msgText.setText(null);
            } catch (IOException el) {
                showArea.append("你的消息：“" + msgText.getText() + "”未能发出去!\n");
            }
        }
    }

    @Override
    //本线程负责将客户机传来的信息显示在对话区域
    public void run() {
        try {
            while (true) {
                showArea.append("客户端说：" + inFromClient.readUTF() + "\n");
                Thread.sleep(1000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

