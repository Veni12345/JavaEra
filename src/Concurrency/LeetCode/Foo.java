package Concurrency.LeetCode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Veni
 * @date: 2022/11/07 十一月 星期一 11:02
 * @description: 1114. 按序打印
 */
public class Foo {

    private final Object object = new Object();
    private volatile int flag = 1;

    public Foo() {

    }


    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (object) {
            while (flag != 1) object.wait();
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            flag = 2;
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (object) {
            while (flag != 2) object.wait();
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            flag = 3;
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (object) {
            while (flag != 3) object.wait();
        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }


}

class Foo2{
    public Foo2() {

    }

    private AtomicInteger fir=new AtomicInteger(0);
    private AtomicInteger sec=new AtomicInteger(0);

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        fir.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (fir.get()!=1){}
        printSecond.run();
        sec.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (sec.get()!=1){}
        printThird.run();
    }
}
