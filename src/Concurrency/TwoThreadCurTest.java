package Concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Veni
 * @date: 2023/03/06 三月 星期一 10:51
 * @description: 两个并行线程池
 */
public class TwoThreadCurTest {

    public static void main(String[] args) {
        final Executor executor = null;
        ExecutorService executorService= Executors.newFixedThreadPool(2);
        AtomicInteger atomicInteger1=new AtomicInteger();
        CountDownLatch countDownLatch=new CountDownLatch(2);
        executorService.execute(()->{
            count(100);
            countDownLatch.countDown();
        });
        executorService.execute(()->{
            count(200);
            countDownLatch.countDown();
        });
        try {
            countDownLatch.await();
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("END");

        AtomicInteger atomicInteger2=new AtomicInteger();
    }

    private static void count(int n){
        for (int i = 0; i < n; i++) {
            System.out.println(Thread.currentThread().getName()+" "+i);
        }
    }
}
