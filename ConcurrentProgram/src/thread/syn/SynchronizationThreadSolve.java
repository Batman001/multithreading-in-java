package thread.syn;

import thread.hero.MyHero;

/**
 * Created by Batman on 2018/10/15.
 * @author Batman
 */
public class SynchronizationThreadSolve {
    public static void main(String[] args) throws InterruptedException {
        final MyHero gareen = new MyHero("盖伦",10000, 100);

        int n=10000;

        Thread[] addThreads = new Thread[n];
        Thread[] reduceThreads = new Thread[n];
        for(int i=0;i<n;i++){
            Thread t = new Thread(){
                @Override
                public void run(){
                    // 任何线程想要修改血量值,必须先占用someObject
                    synchronized (gareen){
                        gareen.recover();
                        System.out.println("当前操作增加血量" + gareen.hp);
                    }
                    try{
                        Thread.sleep(100);
                    }
                    catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            };
            t.start();
            addThreads[i] = t;
        }

        for(int i=0;i<n;i++){
            Thread t = new Thread(){
                @Override
                public void run() {
                    synchronized (gareen) {
                        gareen.hurt();
                        System.out.println("当前操作减少血量" + gareen.hp);
                    }

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
            reduceThreads[i] = t;

        }



        for(Thread t:addThreads){
            try{
                t.join();
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        for(Thread t:reduceThreads){
            try{
                t.join();
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        System.out.printf("%d个增加线程和%d个减少线程结束后%n盖伦的血量是 %.0f%n", n, n, gareen.hp);
    }

}
