package my;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 孟享广
 * @create 2020-07-30 1:32 下午
 */
public class TestReadWriteLock {
    public static void main(String[] args) {
        ReadWriteLockDemo rw = new ReadWriteLockDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                rw.set((int)Math.random()*101);
            }
        }, "Write").start();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    rw.get();
                }
            }).start();
        }

    }
}
class ReadWriteLockDemo{
    private int num = 0;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    //读
    public void get(){
        try {
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName()+":"+num);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }
    //写
    public  void set(int num){
        try {
            lock.writeLock().lock();
            System.out.println(Thread.currentThread().getName());
            this.num = num;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }
}

