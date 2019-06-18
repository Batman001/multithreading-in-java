package thread.function;

import thread.hero.MyHero;

/**
 * Created by Batman on 2018/10/10.
 * @author Batman
 */
public class TestThreadFunc {


    public static void main(String[] args) {
        Thread t1 = new Thread(){
            @Override
            public void run(){
                int seconds = 0;
                while(true){
                    try{
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.printf("t1进程测试进程sleep方法 顺便输出统计的已经玩游戏 %d秒 " + "\n",seconds ++);
                    System.out.println();

                }
            }
        };
        System.out.println("测试多线程的sleep方法中......");
//        t1.start();

        final MyHero gareen = new MyHero();
        gareen.name = "盖伦";
        gareen.hp = 616;
        gareen.damage = 50;

        final MyHero teemo = new MyHero();
        teemo.name = "提莫";
        teemo.hp = 300;
        teemo.damage = 30;

        final MyHero bh = new MyHero();
        bh.name = "赏金猎人";
        bh.hp = 500;
        bh.damage = 65;

        final MyHero leesin = new MyHero();
        leesin.name = "盲僧";
        leesin.hp = 415;
        leesin.damage = 80;

        Thread t2 = new Thread(){
            @Override
            public void run(){
                while(!teemo.isDead()){
                    gareen.attackHero(teemo);
                }
            }

        };
        System.out.println("t2线程是盖伦对提莫持续进攻的线程......");
//        t2.start();
//        代码执行到这里，一直是main线程在运行
//
//        try{
//            //t1线程加入到main线程中来，只有t1线程运行结束，才会继续往下走
//            t1.join();
//        }
//        catch(InterruptedException e){
//            e.printStackTrace();
//        }

        Thread t3 = new Thread(){
            @Override
            public void run(){
                while(!leesin.isDead()){
                    // 临时暂停,使得t1可以占用CPU资源
                    Thread.yield();
                    bh.attackHero(leesin);

                }
            }
        };
        System.out.println("t3线程是赏金猎人对盲僧持续进攻的线程......");
//        t3.start();
//
//         线程优先级设置(这里设置英雄之间的仅供进程优先级)
        t2.setPriority(1);
        t3.setPriority(Thread.MAX_PRIORITY);
        t2.start();
        t3.start();

        // 守护线程
        Thread t4 = new Thread(){
            @Override
            public void run(){
                int seconds = 0;
                while(true){
                    try{
                        Thread.sleep(1);
                    }
                    catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.printf("t4守护进程执行中...守护进程的作用为统计已经玩游戏，输出内容：已经玩游戏时间为%d毫秒%n", seconds++);
                }

            }
//
        };
//        // 如果一个多线程任务重只有守护线程,结束当前进程
        t4.setDaemon(true);
        System.out.println("t4线程是守护线程......");
        t4.start();



    }
}
