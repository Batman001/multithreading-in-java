package thread.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Batman create on 2019-02-25 12:46 AM
 * 使用java自带线程池进行测试生产消费者模型
 */
public class TestThreadPoolInJavaCarry {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 15, 60,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    System.out.println("线程池执行任务" + (i+1));
                }
            }
        });

    }

}
