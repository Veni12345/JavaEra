package Concurrency.AQS源码理解;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/*
		AQS使用了模板方法模式，自定义同步器时需要重写下面几个AQS提供的模板方法：
                isHeldExclusively()//该线程是否正在独占资源。只有用到condition才需要去实现它。
                tryAcquire(int)//独占方式。尝试获取资源，成功则返回true，失败则返回false。
                tryRelease(int)//独占方式。尝试释放资源，成功则返回true，失败则返回false。
                tryAcquireShared(int)//共享方式。尝试获取资源。负数表示失败；0表示成功，但没有剩余可用资源；正数表示成功，且有剩余资源。
                tryReleaseShared(int)//共享方式。尝试释放资源，成功则返回true，失败则返回false。
*/

/**
 * @author: Veni
 * @date: 2023/09/21 九月 星期四 10:00
 * @description: 簡單的不可重入的独占锁
 */
public class AQSCustom extends AbstractQueuedSynchronizer {


    @Override
    protected boolean tryAcquire(int arg) {
        if (compareAndSetState(0, 1)) { // 使用 compareAndSetState 方法设置同步状态
            setExclusiveOwnerThread(Thread.currentThread()); // 设置当前线程为独占所有者
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
        if (getState() == 0)
            throw new IllegalMonitorStateException(); // 如果当前同步状态为0，则抛出异常
        setExclusiveOwnerThread(null); // 清除独占所有者
        setState(0); // 释放同步状态
        return true;
    }

    public void lock() {
        acquire(1); // 获取同步状态
    }

    public void unlock() {
        release(1); // 释放同步状态
    }
}
