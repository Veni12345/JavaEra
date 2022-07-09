package Concurrency;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author: Veni
 * @date: 2022/07/04 七月 星期一 11:05
 * @description: 创建线程的方式
 */
public class CreateThread {
    public static void main(String[] args) throws InterruptedException {
        //	1. 继承Thread类；
        //	2. 实现Runnable接口；
        //	3. 创建带返回值的线程，实现Callable接口；（Future+FutureTask）
        //  4. 利用线程池创建；


        //  5. 通过匿名内部类创建
        new Thread(){
            @Override
            public void run(){
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(i);
                }
            }
        }.start();

        //  6. 通过Timer创建
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(new Date());
            }
        },3000,1000);

        //主线程休眠
        Thread.sleep(1000);
    }


}
