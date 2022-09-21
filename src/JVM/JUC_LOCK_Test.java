package JVM;

import java.util.concurrent.locks.LockSupport;

/**
 * @author: Veni
 * @date: 2022/08/25 八月 星期四 15:34
 * @description:
 */
public class JUC_LOCK_Test {
    public static void main(String[] args) {
        //为什么LockSupport也是核心基础类? AQS框架借助于两个类：Unsafe(提供CAS操作)和LockSupport(提供park/unpark操作)
        LockSupport lockSupport;

        //


    }

//    public static void park(Object blocker) {
//        Thread t = Thread.currentThread();
//        setBlocker(t, blocker);  //
//        U.park(false, 0L);
//        setBlocker(t, null);
//    }
}
