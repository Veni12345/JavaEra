package IO;

import java.io.*;
import java.net.Socket;

public class IODemo {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\Asterion\\Desktop\\Veni\\JavaEra\\file\\rea.txt");
        //输出流测试
        outputStreamTest(file);
        //输入流测试
        inputStreamTest(file);

        //
        Socket socket=new Socket();
        




    }

    private static void outputStreamTest(File file) {
        //判断文件路径是否存在 否则创建
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            //输出流FileOutputStream
            OutputStream outputStream = new FileOutputStream(file);
            String string = "VENI WORLD!";
            outputStream.write(string.getBytes());   //覆盖写
        } catch (IOException e) {
            e.printStackTrace();
        }
//        outputStream.close();   //关闭流文件
    }

    private static void inputStreamTest(File file) {
        //输入流
        try {
            InputStream inputStream = new FileInputStream(file);
            byte data[] = new byte[1024];      //开辟缓存区存储数据
            int len = inputStream.read(data);  //读取数据，数据全部保存至字节数组中，返回读取个数
            System.out.println("[" + new String(data) + "]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
