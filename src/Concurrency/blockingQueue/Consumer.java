package Concurrency.blockingQueue;

public class Consumer implements Runnable{
    BoundedBlockingQueue blockingQueue;

    public Consumer(BoundedBlockingQueue blockingQueue){
        this.blockingQueue=blockingQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            try {
                int dequeue = blockingQueue.dequeue();
                System.out.println("消费："+dequeue);
                Thread.sleep(50);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
