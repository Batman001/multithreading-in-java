package thread.hero;

/**
 * Created by Batman on 2018/10/9.
 * @author:Batman
 */

public class MyHero {
    public String name;
    public float hp;

    public int damage;

    public MyHero(String name, float hp, int damage) {
    }

    public MyHero() {

    }

    public synchronized void recover() {
        hp = hp + 1;
        System.out.printf("%s 回血1点,增加血后，%s的血量是%.0f%n", name, name, hp);
        // 通知那些等待在this对象上的线程，可以醒过来了，如第20行，等待着的减血线程，苏醒过来
        this.notify();
    }

    public synchronized void hurt() {
        if (hp == 1) {
            try {
                // 让占有this的减血线程，暂时释放对this的占有，并等待
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        hp = hp - 1;
        System.out.printf("%s 减血1点,减少血后，%s的血量是%.0f%n", name, name, hp);
    }



    public void attackHero(MyHero h) {
        h.hp-=damage;
        System.out.format("%s 正在攻击 %s, %s的血变成了 %.0f%n",name,h.name,h.name,h.hp);
        if(h.isDead()) {
            System.out.println(h.name + "死了！");
        }
    }

    public boolean isDead() {
        return 0>=hp?true:false;
    }

}
