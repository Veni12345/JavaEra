package Concurrency;

import lombok.SneakyThrows;

/**
 * 多线程案例：设计四个操作线程，两个执行加操作，两个执行减操作，最终结果为0
 */
public class Multi_NumCountDemo {
    public static void main(String[] args) {
        Resource resource = new Resource();
        AddThread addThread1 = new AddThread(resource);
//        Concurrency.AddThread addThread2= new Concurrency.AddThread(resource);
        SubThread subThread1 = new SubThread(resource);
//        Concurrency.SubThread subThread2= new Concurrency.SubThread(resource);
        new Thread(addThread1, "加法线程1").start();
        new Thread(addThread1, "加法线程2").start();
        new Thread(subThread1, "减法线程1").start();
        new Thread(subThread1, "减法线程2").start();
    }
}

/**
 * 公共资源
 */

class Resource {
    private volatile int num;
    private volatile boolean flag = true; //flag为true,正在进行加法操作

    @SneakyThrows
    public synchronized void add() {
        if (this.flag == false) {
            super.wait();
        }
        num++;
        Thread.sleep(100);
        System.out.println("执行加法操作，Thread:" + Thread.currentThread().getName() + " 当前值：" + num);
        this.flag = false;
        super.notifyAll();
    }

    @SneakyThrows
    public synchronized void sub() {
        if (this.flag == true) {
            super.wait();
        }
        num--;
        Thread.sleep(100);
        System.out.println("执行减法操作，Thread:" + Thread.currentThread().getName() + " 当前值：" + num);
        this.flag = true;
        super.notifyAll();
    }
}

class AddThread implements Runnable {
    private final Resource resource;

    public AddThread(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            try {
                resource.add();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class SubThread implements Runnable {
    private final Resource resource;

    public SubThread(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            try {
                resource.sub();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

