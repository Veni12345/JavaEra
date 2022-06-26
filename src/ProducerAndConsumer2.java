import lombok.Data;
import lombok.SneakyThrows;

/**
 * @Author : Created by lity25
 * @Date : 2022/1/12 15:24
 * @Description : 解决 生产者消费者问题
 *
 */
public class ProducerAndConsumer2 {
    public static void main(String[] args) {
        Message2 message=new Message2();
        //启动生产者进程
        new Thread(new Producer2(message)).start();
        //启动消费者进程
        new Thread(new Consumer2(message)).start();

        System.out.println("===========");
    }
}

@Data
class Message2{
    private String title;
    private String mes;
    //flag:true 允许生产，但是不允许消费
    //false 允许消费，但是不允许生产
    private Boolean flag =true;
    public synchronized void set(String title,String mes) throws InterruptedException {
        if(flag==false){  //无法进行生产
            super.wait(10000);
        }
        this.title=title;
        Thread.sleep(100);
        this.mes=mes;
        flag=false;  //生产过了 可以被消费
        super.notify();
    }
    public synchronized String get() throws InterruptedException {
        if(flag==true){  //还未生产，需要等待
            super.wait();
        }
        try {
            return this.title+" - "+this.mes;
        }finally {
            this.flag=true;  //继续生产
            super.notify();  //唤醒等待线程
        }
    }
}

@Data
class Producer2 implements Runnable{
    private Message2 message;
    public Producer2(Message2 message){
        this.message=message;
    }
    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if(i%2==0){
                this.message.set("House Stark","THe North Remember!");
            }else {
                this.message.set("House Lannister","Lannister always Pay!");
            }
        }
    }
}

@Data
class Consumer2 implements Runnable{
    private Message2 message;
    public Consumer2(Message2 message){
        this.message=message;
    }
    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println(this.message.get());
        }
    }
}
