package Concurrency.blockingQueue;

public class Producer implements Runnable {
    BoundedBlockingQueue blockingQueue;

    public Producer(BoundedBlockingQueue blockingQueue){
        this.blockingQueue=blockingQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                blockingQueue.enqueue(i);
                System.out.println("生产："+i);
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
