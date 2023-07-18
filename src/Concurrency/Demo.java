package Concurrency;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: Veni
 * @date: 2022/10/28 十月 星期五 16:53
 * @description:
 */
public class Demo {
    public static void main(String[] args) {
        //new ThreadPoolExecutor.AbortPolicy():达到最大承载量，不再处理，并且抛出异常
        //new ThreadPoolExecutor.CallerRunsPolicy():达到最大承载量，从哪来的去哪里
        //new ThreadPoolExecutor.DiscardPolicy():达到最大承载量，丢掉任务，但不抛出异常
        //new ThreadPoolExecutor.DiscardOldestPolicy():达到最大承载量，尝试与最早执行的线程去竞争，不抛出异常

        //最大线程池大小该如何定义
        //1.cpu密集型，逻辑处理器个数
        //2.io密集型 > 判断程序十分耗IO的线程，最大线程池大小应该比这个大
        int maxPools= Runtime.getRuntime().availableProcessors();
        System.out.println(maxPools);

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,
                maxPools,
                3,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );

        try {
            for (int i = 0; i < 10; i++) {
                //使用线程池来创建线程
                //最大承载：maximumPoolSize+workQueue，超过执行拒绝策略
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+" ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown(); //线程池使用完毕后需要关闭
        }

    }
}

