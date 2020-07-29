package my;

import com.sun.source.tree.NewArrayTree;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 孟享广
 * @create 2020-07-29 11:18 上午
 */
public class TestAtomicDemo {
    public static void main(String[] args) {

        AtomicDemo ad = new AtomicDemo();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(ad);
            thread.start();
        }

    }
}
class AtomicDemo implements  Runnable{
    private AtomicInteger serialNumber  = new AtomicInteger();

    public int getSerialNumber() {
        return serialNumber.getAndIncrement();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getSerialNumber());

    }
}
