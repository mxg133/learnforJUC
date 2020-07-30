package my;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author 孟享广
 * @create 2020-07-30 2:17 下午
 */
public class TestScheduledThreadPool implements Runnable{
    private int i = 0;

    @Override
    public void run() {
        while (i < 10){
            System.out.println(Thread.currentThread().getName() + ":" + i++);
        }
    }

    @Test
    public void test1(){
        TestScheduledThreadPool testScheduledThreadPool = new TestScheduledThreadPool();
//        new Thread(testScheduledThreadPool).start();
//        new Thread(testScheduledThreadPool).start();

        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            threadPool.submit(testScheduledThreadPool);

        }

        threadPool.shutdown();


    }
    @Test
    public void test2() throws Exception {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        Future<Integer> future = threadPool.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int sum = 11110;
                return sum;
            }
        });

        System.out.println(future.get());

        threadPool.shutdown();

    }
}
