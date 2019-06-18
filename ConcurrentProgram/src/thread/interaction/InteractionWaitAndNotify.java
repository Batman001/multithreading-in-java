package thread.interaction;

import thread.hero.MyHero;

/**
 * @author Batman create on 2019-02-25 12:26 AM
 * 使用wait和notify进行线程交互
 */
public class InteractionWaitAndNotify {
    public static void main(String[] args) {

        final MyHero gareen = new MyHero();
        gareen.name = "盖伦";
        gareen.hp = 616;

        Thread t1 = new Thread(){
            @Override
            public void run(){
                while(true){

                    //无需循环判断
//                    while(gareen.hp==1){
//                        continue;
//                    }

                    gareen.hurt();

                    try {
                        // 加快掉血的速度
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }
        };
        t1.start();

        Thread t2 = new Thread(){
            @Override
            public void run(){
                while(true){
                    gareen.recover();

                    try {
                        // 减慢加血的速度
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }
        };
        t2.start();

    }

}
