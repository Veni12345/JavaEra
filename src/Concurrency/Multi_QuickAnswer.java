package Concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 多线程案例：三个答题者进行抢答，一个答题者抢答成功后其他答题者不能再次抢答，并返回“抢答成功”
 */
public class Multi_QuickAnswer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        QuickAnswer quickAnswer = new QuickAnswer();
        FutureTask<String> task1 = new FutureTask<String>(quickAnswer);
        FutureTask<String> task2 = new FutureTask<String>(quickAnswer);
        FutureTask<String> task3 = new FutureTask<String>(quickAnswer);
        new Thread(task1, "竞争者1").start();
        new Thread(task2, "竞争者2").start();
        new Thread(task3, "竞争者3").start();
        System.out.println(task1.get());
        System.out.println(task2.get());
        System.out.println(task3.get());

    }
}

class QuickAnswer implements Callable<String> {
    private boolean flag = false; //抢答标识

    @Override
    public String call() throws Exception {
        synchronized (this) {
            if (this.flag == false) {
                //可以进行抢答
                this.flag = true;
                return Thread.currentThread().getName() + "抢答成功！";
            } else {
                return Thread.currentThread().getName() + "抢答失败！";
            }
        }
    }
}
