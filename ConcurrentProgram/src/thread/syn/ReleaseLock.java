package thread.syn;

/**
 * @author Batman create on 2019-02-24 9:55 PM
 * 执行同步方法中出现异常，那么就会自动释放锁
 */
public class ReleaseLock {
    private int count = 0;
    private int i = 0;

    public synchronized void m1() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " " + count++);
            if (count % 10 == 0) {
                i = 1 / 0;
            }
        }
    }

    public static void main(String[] args) {
        ReleaseLock releaseLock = new ReleaseLock();
        new Thread(() -> releaseLock.m1(), "t1").start();
        new Thread(() -> releaseLock.m1(), "t2").start();
    }
}
