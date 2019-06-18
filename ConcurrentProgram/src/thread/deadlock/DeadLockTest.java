package thread.deadlock;

/**
 * @author Batman create on 2019-02-24 9:53 PM
 */

public class DeadLockTest {
    public static void main(String[] args) {
        final Object a = new Object();
        final Object b = new Object();
        final Object c = new Object();

        Thread t1 = new Thread(){
            @Override
            public void run(){
                synchronized (a){
                    System.out.println("t1 posses Object a");
                    try{
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println("t1 try to get Object b");
                    System.out.println("t1 waitting....");
                    synchronized (b){
                        System.out.println("t1 succeed to get Object b");
                    }
                }

            }
        };
        t1.start();
        Thread t2 = new Thread(){
            @Override
            public void run(){
                synchronized (b){
                    System.out.println("t2 posses Object b");
                    try{
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println("t2 try to get Object c");
                    System.out.println("t2 waitting....");
                    synchronized (c){
                        System.out.println("t2 succeed to get Object C");
                    }
                }

            }
        };
        t2.start();
        Thread t3 = new Thread(){
            @Override
            public void run(){
                synchronized (c){
                    System.out.println("t3 posses Object c");
                    try{
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println("t3 try to get Object a");
                    System.out.println("t3 waitting....");
                    synchronized (a){
                        System.out.println("t3 succeed to get Object a");
                    }
                }

            }
        };
        t3.start();


    }
}


