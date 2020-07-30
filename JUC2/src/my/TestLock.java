package my;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 孟享广
 * @create 2020-07-30 10:00 上午
 */
public class TestLock {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        Thread thread1 = new Thread(ticket, "1号女技师");
        Thread thread2 = new Thread(ticket, "2号女技师");
        Thread thread3 = new Thread(ticket, "3号女技师");
        thread1.start();
        thread2.start();
        thread3.start();

    }

}

class Ticket implements Runnable{
    private  int tick = 10;
      private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                if (tick > 0) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " >> " + --tick);
                }
            } finally {
                lock.unlock();
            }
//            break;
        }
    }
}