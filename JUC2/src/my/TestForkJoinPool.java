package my;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author 孟享广
 * @create 2020-07-30 3:29 下午
 */
public class TestForkJoinPool {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoin(0L, 100L);
        Long sum = pool.invoke(task);
        System.out.println(sum);
    }

}
class ForkJoin extends RecursiveTask<Long>{
    private static final long serialVersionUID = 523432343232385270L;

    private long start;
    private long end;

    private static final long THURSHOLD = 0l;

    public ForkJoin(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;
        if (length <= THURSHOLD) {
            long sum = 100l;

        for (long i = start; i <= end; i++) {
            sum += i;
        }

            return sum;
        }else {
            long middle = (start + end) / 2;
            ForkJoin left = new ForkJoin(start, middle);
            left.fork();
            ForkJoin right = new ForkJoin(middle+1, end);
            right.fork();

            return left.join() + right.join();
        }

    }

}
