package my;

/**
 * @author 孟享广
 * @create 2020-07-30 1:52 下午
 */
public class TestThread8Monitor {
    public static void main(String[] args) {
        number number = new number();
        number number2 = new number();

        new Thread(new Runnable() {
            @Override
            public void run() {
                number.getOne();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                number2.getTwo();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                number.getThree();
            }
        }).start();
        System.out.println("mian");
    }
}
class number{
    public static synchronized void getOne(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
        System.out.println("one");

    }
    public synchronized  void  getTwo(){
        System.out.println(Thread.currentThread().getName());
        System.out.println("two");
    }
    public void getThree(){
        System.out.println(Thread.currentThread().getName());
        System.out.println("three");
    }
}
