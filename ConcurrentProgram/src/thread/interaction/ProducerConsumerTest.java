package thread.interaction;

import thread.safe.MySafeStack;

/**
 * Created by Batman on 2018/10/17.
 * @author Batman
 * 生产者消费者问题是一个非常典型性的线程交互的问题。
 * 1. 使用栈来存放数据
 * 1.1 把栈改造为支持线程安全
 * 1.2 把栈的边界操作进行处理，当栈里的数据是0的时候，访问pull的线程就会等待。 当栈里的数据是200的时候，访问push的线程就会等待
 * 2. 提供一个生产者（Producer）线程类，生产随机大写字符压入到堆栈
 * 3. 提供一个消费者（Consumer）线程类，从堆栈中弹出字符并打印到控制台
 */
public class ProducerConsumerTest {
    public static class ProduceThread extends Thread {
        private MySafeStack<Character> stack;
        public ProduceThread(MySafeStack<Character> stack, String name) {
            super(name);
            this.stack = stack;
        }
        @Override
        public void run(){
            while(true){
                char c = randomChar();
                System.out.println(this.getName() + " 压入" + c);
                stack.push(c);
                System.out.println("压入字符后,栈的空间为: "+stack.size());

                try{
                    Thread.sleep(100);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }

            }
        }
        public char randomChar(){
            return (char)(Math.random() * ('Z' + 1 - 'A') + 'A');
        }
    }
    public static class ConsumerThread extends Thread{
        private MySafeStack<Character> stack;

        public ConsumerThread(MySafeStack<Character> stack, String name){
            super(name);
            this.stack = stack;
        }
        @Override
        public void run(){
            while(true){
                char c = stack.pull();
                System.out.println(this.getName() + " 弹出" + c);
                System.out.println("弹出该字符后,栈的空间为: "+stack.size());
                try{
                    Thread.sleep(100);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }

    }
    public static void main(String[] args) {
        MySafeStack<Character> stack = new MySafeStack<>();
//        ProducerConsumerTest pct = new ProducerConsumerTest();
        new ProduceThread(stack,"producer1").start();
        new ProduceThread(stack,"producer2").start();
//        new ConsumerThread(stack,"consumer1").start();
//        new ConsumerThread(stack,"consumer2").start();
        new ConsumerThread(stack,"consumer3").start();

    }
}
