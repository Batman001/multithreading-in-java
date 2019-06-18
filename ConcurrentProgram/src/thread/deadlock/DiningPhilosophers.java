package thread.deadlock;

/**
 * @author Batman create on 2019-06-17 09:40
 */
public class DiningPhilosophers {
    public static void main(String[] args) {
        args = new String[4];
        // 哲学家数
        args[0] = "5";
        // 思考时间
        args[1] = "0";
        // 指定发生死锁
        args[2] = "deadlock";

        Philosopher[] philosopher = new Philosopher[Integer.parseInt(args[0])];
        Philosopher.ponder = Integer.parseInt(args[1]);

        // first指向left指向的地址,left重新指向其他地方,first不跟随改变
        Chopstick left = new Chopstick(), right = new Chopstick(), first = left;
        int i = 0;
        while (i < philosopher.length - 1) {
            philosopher[i++] = new Philosopher(left, right);
            left = right;
            right = new Chopstick();
        }

        if (("deadlock").equals(args[2])) {
            philosopher[i] = new Philosopher(left, first);
        }
        else {
            // 交换最后一个哲学家拿筷子的顺序,打破死锁
            philosopher[i] = new Philosopher(first, left);
        }

    }
}
