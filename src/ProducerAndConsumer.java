import lombok.Data;

/**
 * @Author : Created by lity25
 * @Date : 2022/1/12 15:24
 * @Description : 生产者消费者问题：生产者与消费者线程同时访问同一资源
 * 出現問題
 *
 */
public class ProducerAndConsumer {
    public static void main(String[] args) {
        Message message=new Message();
        //启动生产者进程
        new Thread(new Producer(message)).start();
        //启动消费者进程
        new Thread(new Consumer(message)).start();

    }
}

@Data
class Message{
    private String title;
    private String mes;
}

@Data
class Producer implements Runnable{
    private Message message;
    public Producer(Message message){
        this.message=message;
    }
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i%2==0){
                this.message.setTitle("House Stark");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.message.setMes("THe North Remember!");
            }else {
                this.message.setTitle("House Lannister");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.message.setMes("Lannister always Pay!");
            }
        }
    }
}

@Data
class Consumer implements Runnable{
    private Message message;
    public Consumer(Message message){
        this.message=message;
    }
    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.message.getTitle()+" - "+this.message.getMes());
        }
    }
}
