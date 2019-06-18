package thread.syn;

/**
 * @author Batman create on 2019-02-24 9:19 PM
 * 对业务写代码进行加锁，对读代码不进行加锁，会产生脏读
 */
public class DirtyRead {
    private String name;
    private double balance;

    public synchronized void set(String name, double balance) {
        this.name = name;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public static void main(String[] args) {
        DirtyRead dr = new DirtyRead();
        new Thread(() -> dr.set("Batman", 500)).start();
        System.out.println(dr.getBalance());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(dr.getBalance());
    }
}
