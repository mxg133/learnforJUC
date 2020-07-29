package my;

/**
 * @author 孟享广
 * @create 2020-07-29 4:52 下午
 */
public class TestCompareAndSwap {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final CompareAndSwap cas = new CompareAndSwap();

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    int expectValue  = cas.getValue();
                    boolean b = cas.compareAndSet(expectValue, (int) (Math.random() * 101));
                    System.out.println(b);

                }
            });
            thread.start();




        }
    }

}

class CompareAndSwap {
    private int value;
//获取
    public synchronized int getValue() {
        return value;
    }
    //比较
    public synchronized int compareAndSwap(int expectedValue, int newValue){
        int oldValue = value;
        if (oldValue == expectedValue){
            this.value = newValue;
        }
        return oldValue;
    }
//设置
    public synchronized boolean compareAndSet(int expectedValue, int newValue){
        return expectedValue == compareAndSwap(expectedValue, newValue);

    }
}
