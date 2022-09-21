package JVM;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Veni
 * @date: 2022/08/26 八月 星期五 14:24
 * @description: 分析AbstractQueuedSyncrhonizer内部的工作机制
 */
/*
类的核心方法 - acquire方法:
    该方法以独占模式获取(资源)，忽略中断，即线程在acquire方法过程中，中断此线程是无效的
 */
public class AbstractQueuedSynchonizerDemo {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        MyThread t1 = new MyThread("t1", lock);
        MyThread t2 = new MyThread("t2", lock);
        t1.start();
        t2.start();
    }
}


class MyThread extends Thread {
    private Lock lock;
    public MyThread(String name, Lock lock) {
        super(name);
        this.lock = lock;
    }

    public void run () {
        lock.lock();
        try {
            System.out.println(Thread.currentThread() + " running");
        } finally {
            lock.unlock();
        }
    }
}
