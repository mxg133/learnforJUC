package my;

/**
 * @author 孟享广
 * @create 2020-07-26 4:59 下午
 */
public class TestVolatile {
    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();
        Thread thread = new Thread(td);
        thread.start();

        while (true){
//            synchronized (td) {

                if (td.isFlag()) {
                    System.out.println("1111");
                    break;


                }
//            }
        }
    }
}

class ThreadDemo implements Runnable{

    private volatile boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = true;

        System.out.println("flag" + isFlag());
    }
}
