package thread.start;

import thread.hero.MyHero;

/**
 * Created by Batman on 2018/10/9.
 * 继承Thread的子类  然后重写run方法
 * @author Batman
 */
public class KillThread extends Thread {
    private MyHero attack;
    private MyHero bear;

    public KillThread(MyHero mh1, MyHero mh2){
        this.attack = mh1;
        this.bear = mh2;
    }

    @Override
    public void run(){
        while(!bear.isDead()){
            attack.attackHero(bear);
        }
    }

}
