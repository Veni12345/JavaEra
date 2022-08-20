package Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class Multi_Test {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        //CachedThreadPool: 一个任务创建一个线程
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        for (int i = 0; i < 5; i++) {
//            executorService.execute(new Concurrency.MyRunnable() {
//                public void run() {
//                    System.out.println("Concurrency.MyRunnable run...");
//                }
//            });
//        }
//        executorService.shutdown();

    }

}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Concurrency.MyRunnable run...");
    }
}
