/**
 * synchronized
 *
 * 在上述代码中，在main函数中分别启动了A线程和B线程，为了让线程A先获取到资源，这里让线程B休眠了1s，
 * 线程A先获取到共享变量resourceA和共享变量resourceB的锁，然后resourceA的wait()方法挂起了自己，并释放了resourceA的锁。
 *
 * 线程B休眠结束后，肯定先尝试获取resourceA上的锁，如果当时线程A还没有调用wait()方法释放该锁，则线程B会被阻塞，
 * 当线程A释放了resourceA上的锁后，线程B就会获取到resourceA上的锁，然后尝试获取resourceB上的锁。
 * 由于线程A调用的是resourceA上的wait()方法，所以并没有将resourceB上的锁给释放掉，当线程B尝试获取resourceB上的锁时就会被阻塞。
 *
 * 总结: 当前线程调用共享变量对象的wait()方法时，当前线程只会释放当前共享对象的锁，当前线程持有的其他共享对象的监视器锁并不会被释放。
 * ————————————————
 * 版权声明：本文为CSDN博主「Nil~」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/qq_38215702/article/details/89743601
 */
public class Multi_Synchronized {
    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(new ResourceA());
        Thread b = new Thread(new ResourceB());

        a.start();
        b.start();

        a.join();
        b.join();
    }
    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static class ResourceA implements Runnable {
        @Override
        public void run() {
            try{
                // 获取resourceA的共享资源监视器锁
                synchronized (resourceA) {
                    System.out.println("获取线程A中resourceA的锁");
                    // 获取resourceB的共享资源的监视器锁
//                    synchronized (resourceB) {
                        System.out.println("获取线程A中resourceB的锁");
                        // 线程A阻塞，并释放获取到的resourceA的锁
                        System.out.println("将线程A挂起，并释放resourceA的锁");
                        resourceA.wait();
                    }
//                }
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class ResourceB implements Runnable {
        @Override
        public void run() {
            try{
                // 休眠1s
                Thread.sleep(1000);
                // 获取resourceA的共享资源监视器锁
                synchronized (resourceA) {
                    System.out.println("获取线程B中resourceA的锁");
                    System.out.println("获取线程B中resourceB的锁");
                    // 获取resourceB的共享资源的监视器锁
                    synchronized (resourceB) {
                        System.out.println("获取线程B中resourceB的锁...");
                        // 线程B阻塞，并释放获取到的resourceA的锁
                        System.out.println("获取线程B中resourceB的锁...");
                        resourceA.wait();
                    }
                }
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
