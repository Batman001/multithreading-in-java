package thread.safe;

import java.util.LinkedList;

/**
 * Created by Batman on 2018/10/15.
 * @author Batman
 * 把栈改造为支持线程安全
 * 把栈的边界操作进行处理:
 * 当栈里的数据是0的时候，访问pull的线程就会等待。
 * 当栈里的数据时200的时候，访问push的线程就会等待
 */
public class MySafeStack<T> {
    LinkedList<T> values = new LinkedList<T>();
    public synchronized void push(T t){
        while(values.size()>=200){
            try{
                this.wait();
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        this.notifyAll();
        values.addLast(t);
    }

    public synchronized T pull(){
        while(values.isEmpty()){
            try{
                this.wait();
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        this.notifyAll();
        return values.removeLast();
    }

    public T peek(){
        return values.getLast();
    }
    public int size(){
        return values.size();
    }



}
