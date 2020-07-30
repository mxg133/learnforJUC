package my;

import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author 孟享广
 * @create 2020-07-30 9:43 上午
 */
public class TestCallable {
    public static void main(String[] args) {
        ThreadDemo demo = new ThreadDemo();

        FutureTask<Integer> futureTask = new FutureTask<Integer>(demo);

        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println("-------------------");
        //接受结果
        try {
            Integer integer = futureTask.get();
            System.out.println(integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

}

class ThreadDemo implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        int sum  = 0;
        for (int i = 0; i < 10; i++) {
//            System.out.println( i);
            sum += (i+1);
        }
        return  sum;
    }
}
