package IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: Veni
 * @date: 2024/04/10 四月 星期三 22:04
 * @description:
 */
public class IOTest {
    public static void main(String[] args) {
        //输入流
        try {
            InputStream inputStream = new FileInputStream("C:\\Users\\Asterion\\Desktop\\Veni\\JavaEra\\src\\IO\\file.txt");
            byte data[] = new byte[1024];      //开辟缓存区存储数据
            int len = inputStream.read(data);  //读取数据，数据全部保存至字节数组中，返回读取个数
            System.out.println("[" + new String(data) + "]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
