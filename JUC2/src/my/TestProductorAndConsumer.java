package my;

/**
 * @author 孟享广
 * @create 2020-07-30 10:37 上午
 */
public class TestProductorAndConsumer {
    public static void main(String[] args) {
        Cleck cleck = new Cleck();
        Productor productor = new Productor(cleck);
        Comsumer comsumer = new Comsumer(cleck);

        new Thread(productor, "生产者A").start();
        new Thread(comsumer, "消费者B").start();

        new Thread(productor, "生产者C").start();
        new Thread(comsumer, "消费者D").start();

    }

}
class Cleck{//店员
    private int  product = 0;
    //进货---生产者
    public synchronized void get(){
        while (product >= 1){//为了避免虚假唤醒问题，应该总是使用在循环中
            System.out.println("产品满了，没法添加了。。。");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            System.out.println(Thread.currentThread().getName() + ":" + ++product);
            this.notifyAll();
    }
    //卖货--消费者
    public synchronized void sale(){
        while (product <= 0){
            System.out.println("缺货，不能继续售卖！");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            System.out.println(Thread.currentThread().getName() + ":" + --product);
            this.notifyAll();
    }
}
//生产者
class Productor implements Runnable{
    private Cleck cleck;

    public Productor(Cleck cleck) {
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
class Comsumer implements Runnable{
    private Cleck cleck;

    public Comsumer(Cleck cleck) {
        this.cleck = cleck;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            cleck.sale();
        }
    }
}



