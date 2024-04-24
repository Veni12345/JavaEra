package Concurrency;

/**
 * @author: Veni
 * @date: 2024/03/29 三月 星期五 14:30
 * @description:
 */
public class InterruptExample {
    private static class MyThread1 extends Thread {
        @Override
        public void run() {
            System.out.println("start-----------");
            try {
                Thread.sleep(2000);
                System.out.println("Thread run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end***************");

        }
    }

    //通过调用一个线程的 interrupt() 来中断该线程，
    // 如果该线程处于阻塞、限期等待或者无限期等待状态，
    // 那么就会抛出 InterruptedException，从而提前结束该线程。
    // 但是不能中断 I/O 阻塞和 synchronized 锁阻塞。
    //------
    //著作权归@pdai所有
    //原文链接：https://pdai.tech/md/java/thread/java-thread-x-thread-basic.html
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new MyThread1();
        Thread thread2 = new MyThread1();
        thread1.start();
        thread2.start();
        thread1.interrupt();//在 main() 中启动一个线程之后再中断它，
        // 由于线程中调用了 Thread.sleep() 方法，因此会抛出一个 InterruptedException，从而提前结束线程
        System.out.println("Main run");
    }

}


