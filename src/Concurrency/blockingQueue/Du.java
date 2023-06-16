package Concurrency.blockingQueue;

import java.util.LinkedList;
import java.util.List;

public class Du {
    public static void main(String[] args) {
        BoundedBlockingQueue blockingQueue = new BoundedBlockingQueue(10);

        List<Integer> quuuu = new LinkedList<Integer>();

        Thread pro = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        blockingQueue.enqueue(i);
                        System.out.println("生产：" + i);
                        Thread.sleep(25);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        Thread con = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 200; i++) {
                    try {
                        int dequeue = blockingQueue.dequeue();
                        System.out.println("消费：" + dequeue);
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
//        pro.start();
        Producer producer1 = new Producer(blockingQueue);
        new Thread(producer1).start();
        new Thread(producer1).start();
//        new Thread(producer1).start();
        Consumer consumer = new Consumer(blockingQueue);
        new Thread(consumer).start();
    }
}
