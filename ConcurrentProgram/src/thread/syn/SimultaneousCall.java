package thread.syn;

/**
 * @author Batman create on 2019-02-24 9:14 PM
 * 同一个资源，同步和非同步的方法可以同时调用
 * 可以看到t1先执行，如果不能同时调用那么t2是不能执行的，
 * 必须等t1结束，释放锁后才能调用，但这里t2确先执行了，所以是可以同时调用的。
 */

public class SimultaneousCall {
    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + " m1 begin---------");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m1 end---------");
    }

    public void m2() {
        System.out.println(Thread.currentThread().getName() + " m2 begin---------");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m2 end---------");
    }
    public static void main(String[] args) {
        SimultaneousCall t = new SimultaneousCall();
//        new Thread(()->t.m1(),"t1").start();
//        new Thread(()->t.m2(),"t2").start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                t.m1();

            }
        },"t1").start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                t.m2();

            }
        },"t2").start();
    }
}


