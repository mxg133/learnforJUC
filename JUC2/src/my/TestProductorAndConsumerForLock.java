package my;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 孟享广
 * @create 2020-07-30 12:44 下午
 */
public class TestProductorAndConsumerForLock {

    public static void main(String[] args) {
        Check cleck = new Check();
        Predictor productor = new Predictor(cleck);
        Commuter comsumer = new Commuter(cleck);

        new Thread(productor, "生产者A").start();
        new Thread(comsumer, "消费者B").start();

        new Thread(productor, "生产者C").start();
        new Thread(comsumer, "消费者D").start();

    }
}
class Check {//店员
    private int  product = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    //进货---生产者
    public  void get(){
        lock.lock();
        try {
            while (product >= 1){//为了避免虚假唤醒问题，应该总是使用在循环中
                System.out.println("产品满了，没法添加了。。。");
                try {
//                    this.wait();
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + ":" + ++product);
//            this.notifyAll();
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    //卖货--消费者
    public  void sale(){
        lock.lock();
        try {
            while (product <= 0){
                System.out.println("缺货，不能继续售卖！");
                try {
//                    this.wait();
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + ":" + --product);
//            this.notifyAll();
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
//生产者
class Predictor implements Runnable{
    private Check cleck;

    public Predictor(Check cleck) {
        this.cleck = cleck;
    }


    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cleck.get();
        }
    }
}
//消费者
class Commuter implements Runnable{
    private Check cleck;

    public Commuter(Check cleck) {
        this.cleck = cleck;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            cleck.sale();
        }
    }
}
