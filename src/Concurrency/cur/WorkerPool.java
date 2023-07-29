package Concurrency.cur;

import java.util.concurrent.*;

public class WorkerPool {
    public static void main(String args[]) throws InterruptedException{
        //RejectedExecutionHandler implementation
        RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();
        //Get the ThreadFactory implementation to use
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        //creating the ThreadPoolExecutor
//        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(
//                2, 5, 10, TimeUnit.SECONDS,
//                new ArrayBlockingQueue<Runnable>(2), threadFactory, rejectionHandler);

        //使用SynchronousQueue 并使用CallerRunsPolicy作为拒绝策略，确保任务最终会被执行
        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(
                2, 5, 10, TimeUnit.SECONDS,
                new SynchronousQueue<>(), threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());

        //start the monitoring thread
        MyMonitorThread monitor = new MyMonitorThread(executorPool, 3);
        Thread monitorThread = new Thread(monitor);
        monitorThread.start();

        //submit work to the thread pool
        for(int i=0; i<20; i++){
            executorPool.execute(new WorkerThread("cmd"+i));
        }

        Thread.sleep(30000);
        //shut down the pool
        executorPool.shutdown();
        //shut down the monitor thread
        Thread.sleep(5000);
        monitor.shutdown();

    }

}
