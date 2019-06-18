package thread.syn; /**
 * @author Batman create on 2019-02-24 9:58 PM
 */
/**
 * volatile :易变的 不稳定的
 * volatile关键字   每个线程都有自己的一小块内存，执行的时候会把变量copy过来，修改了后在写回对象，
 * 执行m1方法的线程把 running读到内存里，与此同时主线程也把running读到内存，并进行修改，写回对象为false
 * 但是执行m1的线程里的内存一直都是true啊（因为太忙了没空去刷新）所以会形成死循环，volatile就是当running改了之后
 * 立马去通知其他线程，你们记得去主存刷新一下，一刷新，running为false，退出while循环。
 * @author Batman
 *
 */
public class VolatileDemo {
    private volatile boolean running = true;

    public void m1() {
        System.out.println("m1 start");
        while (running) {

        }
        if(!running){

            System.out.println("此时由于主线程将running置为false,则通过volatile修饰的running变量被修改,立即去通知m1线程,跳出while循环");
        }
        System.out.println("m1 end");
    }

    public static void main(String[] args) {
        VolatileDemo volatileDemo = new VolatileDemo();
        new Thread(() -> volatileDemo.m1()).start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("此时线程: " + Thread.currentThread() + " 修改running变量,将其置为false");
        volatileDemo.running = false;


    }
}
