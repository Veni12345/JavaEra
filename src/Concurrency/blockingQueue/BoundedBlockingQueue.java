package Concurrency.blockingQueue;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自定义阻塞队列
 */
public class BoundedBlockingQueue {
    private LinkedList<Integer> queue = new LinkedList<>();
    private int size = 0;
    private Integer cap = null;
    private ReentrantLock lock = new ReentrantLock();

    private Condition empty = lock.newCondition();
    private Condition full = lock.newCondition();

    public BoundedBlockingQueue(int capacity) {
        if (cap == null) {
            lock.lock();
            try {
                if (cap == null) {
                    cap = capacity;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * 队首增加
     *
     * @param element
     */
    void enqueue(int element) throws InterruptedException {
        lock.lock();
        try {
            while (size == cap) {
                full.await();
            }
            queue.offerFirst(element);
            size+=1;
            empty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 队尾删除
     *
     * @return
     */
    int dequeue() throws InterruptedException{
        lock.lock();
        int ret=-1;
        try{
            while (size==0){
                empty.await();
            }
            ret=queue.pollLast();
            size-=1;
            full.signalAll();
        }finally {
            lock.unlock();
        }
        return ret;
    }

    int size() {   //返回队列中元素数目
        return size;
    }
}
