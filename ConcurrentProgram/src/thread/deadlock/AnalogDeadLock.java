package thread.deadlock;

/**
 * @author Batman create on 2019-02-24 9:27 PM
 * 1. 线程1 首先占有对象1，接着试图占有对象2
 * 2. 线程2 首先占有对象2，接着试图占有对象1
 * 3. 线程1 等待线程2释放对象2
 * 4. 与此同时，线程2等待线程1释放对象1
 * 就会。。。一直等待下去，直到天荒地老，海枯石烂，山无棱 ，天地合。。。
 *
 */
public class AnalogDeadLock {
    private Object o1 = new Object();
    private Object o2 = new Object();

    public void m1() {
        synchronized (o1) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (o2) {
                System.out.println("如果出现这句话表示没有死锁");
            }
        }

    }

    public void m2() {
        synchronized(o2) {

            synchronized (o1) {
                System.out.println("如果出现这句话表示没有死锁");
            }

        }

    }
    public static void main(String[] args) {
        AnalogDeadLock deadLock=new AnalogDeadLock();
        new Thread(()->deadLock.m1()).start();
        new Thread(()->deadLock.m2()).start();
    }
}