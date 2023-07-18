package IO.socket;

import java.io.*;
import java.net.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class ChatClient implements ActionListener, Runnable {
    JTextArea showArea;
    JTextField msgText;
    JFrame mainJframe;
    JButton sentBtn;
    JScrollPane JSPane;
    JPanel pane;
    Container con;
    Thread thread = null;
    Socket connectToServer;
    DataInputStream inFromServer;
    DataOutputStream outToServer;

    public ChatClient() {
        mainJframe = new JFrame("聊天——客户端");
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

        //创建套接字连接到服务器
        try {
            connectToServer = new Socket("localhost", 5500);
            inFromServer = new DataInputStream(connectToServer.getInputStream());
            outToServer = new DataOutputStream(connectToServer.getOutputStream());
            showArea.append("连接成功，请说话...\n");

            //创建线程在后台处理对方的消息
            thread = new Thread(this);
            thread.setPriority(Thread.MIN_PRIORITY);
            thread.start();
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            showArea.append("抱歉，未能连接到服务器！\n");
            msgText.setEditable(false);
            sentBtn.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        new ChatClient();
    }


    @Override
    //
    public void actionPerformed(ActionEvent e) {
        String s = msgText.getText();
        if (s.length() > 0) {
            try {
                outToServer.writeUTF(s);
                outToServer.flush();
                showArea.append("我（客户端）说： " + msgText.getText() + "\n");
            } catch (IOException e1) {
                showArea.append("你的消息：“" + msgText.getText() + "”未能发送出去！\n");
            }
        }

    }

    //本线程负责将服务器传来的消息显示在对话区域
    public void run() {
        try {
            while (true) {
                showArea.append("服务端说：" + inFromServer.readUTF() + "\n");
                Thread.sleep(1000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}


