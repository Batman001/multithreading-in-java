package thread.deadlock;

import java.util.Random;

/**
 * @author Batman create on 2019-06-17 09:34
 * 多线程死锁 之 哲学家进餐
 * 同时满足以下四个条件时,就会发生死锁
 * 1. 互斥条件:线程使用的资源中至少有一个是不能共享的.这里,一根筷子一次就只能被一个哲学家使用.
 * 2. 至少有一个线程持有一个资源,并且它在等待获取一个当前被别的线程持有的资源.也就是说,要放生死锁,哲学家必须拿着一根筷子并且等待另一根.
 * 3. 资源不能被进程(线程)抢占.所有的进程必须把资源释放作为普通条件.哲学家很有礼貌,不会从其他哲学家那里抢筷子.
 * 4. 必须有循环等待,这时,一个进程等待其他进程持有的资源,后者又在等待另外一个进程持有的资源,这样一直下去,
 * 直到有一个进程在等待第一个进程(线程)持有的资源,使得大家都被锁住.
 * 在这里,以为每个哲学家都试图先得到左边的筷子,然后得到右边的筷子,所以发生了循环等待.
 * 在下面的例子中,针对最后一个哲学家,通过交换构造器中的初始化顺序,打破了死锁条件,使得
 * 最后一个哲学家先拿右边的筷子,再拿左边的筷子.
 */

class Chopstick {
    private static int counter = 0;
    private int number = counter ++;


    @Override
    public String toString() {
        return "Chopstick " + number;
    }

}

/**
 * 哲学家的类
 * @author:Batman
 */
public class Philosopher extends Thread {
    private static Random rand = new Random();
    private static int counter = 0;
    private int number = counter++;
    private Chopstick leftChopstick;
    private Chopstick rightChopstick;
    /**
     * Package access
     */
    static int ponder = 0;


    public Philosopher(Chopstick left, Chopstick right) {
        leftChopstick = left;
        rightChopstick = right;
        start();
    }


    public void think() {
        System.out.println(this + " thinking");
        if (ponder > 0) {
            try {
                sleep(rand.nextInt(ponder));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void eat() {
        synchronized (leftChopstick) {
            System.out.println(this + " has " + this.leftChopstick
                    + " Waiting for " + this.rightChopstick);
            synchronized (rightChopstick) {
                System.out.println(this + " eating");
            }
        }
    }

    @Override
    public String toString() {
        return "Philosopher " + number;
    }

    @Override
    public void run() {
        while (true) {
            think();
            eat();
        }
    }



}
