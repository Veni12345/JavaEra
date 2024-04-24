package Concurrency.cur;

import java.util.concurrent.*;

/**
 * 线程池工作原理
 * 测试
 */
public class WorkerPool {
    public static void main(String args[]) throws InterruptedException {
        //RejectedExecutionHandler implementation
        RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();
        //Get the ThreadFactory implementation to use
        ThreadFactory threadFactory = Executors.defaultThreadFactory();


        //creating the ThreadPoolExecutor
        //1. 有界阻塞队列+拒绝策略    超出最大核心线程池的任务直接被拒绝
//        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(
//                2, 5, 10, TimeUnit.SECONDS,
//                new ArrayBlockingQueue<Runnable>(2), threadFactory, rejectionHandler);
        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(
                2, 5, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(2), threadFactory, rejectionHandler);


        //2. 无界阻塞队列+拒绝策略     无界阻塞队列使最大核心线程池、拒绝策略 无效，任务顺序执行
        //此时使用 SynchronousQueue，依然会造成任务拒绝
        /*ThreadPoolExecutor executorPool = new ThreadPoolExecutor(
                2, 5, 10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), threadFactory, rejectionHandler);*/
//        ThreadPoolExecutor executorPool1 = new ThreadPoolExecutor(
//                2, 5, 10, TimeUnit.SECONDS,
//                new SynchronousQueue<>(), threadFactory, rejectionHandler);


        //3. 使用SynchronousQueue 并使用CallerRunsPolicy作为拒绝策略，确保任务最终会被执行
        /*ThreadPoolExecutor executorPool = new ThreadPoolExecutor(
                2, 5, 10, TimeUnit.SECONDS,
                new SynchronousQueue<>(), threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
*/

        //start the monitoring thread
        MyMonitorThread monitor = new MyMonitorThread(executorPool, 3);
        Thread monitorThread = new Thread(monitor);
        monitorThread.start();

        //submit work to the thread pool
        for (int i = 0; i < 20; i++) {
            executorPool.execute(new WorkerThread("cmd" + i));
        }

        Thread.sleep(30000);
        //shut down the pool
        executorPool.shutdown();
        //shut down the monitor thread
        Thread.sleep(5000);
        monitor.shutdown();

    }

}
