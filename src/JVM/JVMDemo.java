package JVM;

import java.net.URLClassLoader;
import java.security.SecureClassLoader;
import java.util.concurrent.ConcurrentMap;

public class JVMDemo {
    static {
        i=0;
//        System.out.print(i);  //初始化时：非法向前引用
    }
    static int i=1;

    public static void main(String[] args) {
        JVMDemo jvmDemo=new JVMDemo();


        //
//        Class.forName()
    }
}
