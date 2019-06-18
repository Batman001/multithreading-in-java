package thread.hero;

/**
 * Created by Batman on 2018/10/9.
 * @author Batman
 */
public class Battle implements Runnable{
    private MyHero attack;
    private MyHero bear;

    public Battle(MyHero mh1, MyHero mh2){
        this.attack = mh1;
        this.bear = mh2;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        while(!attack.isDead()){
            attack.attackHero(bear);
        }

    }
}
