package unsafe;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

import static java.lang.Thread.sleep;

/*
https://juejin.cn/post/6844903913028599821 这里有park的底层实现
https://zeral.cn/java/unsafe.park-vs-object.wait/ park与object的wait区别 !!! => 重点关注

 */
public class ThreadUsage {
    public void threadApi() throws InterruptedException {
        AtomicInteger round = new AtomicInteger(0);
        Thread a = new Thread(() -> {
            while (true) {
                System.out.println("hello world " + round.get());
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                round.incrementAndGet();
                if(round.get() % 3 == 0){
                    System.out.println("park");
                    long before = System.currentTimeMillis();
                    //LockSupport的park方法底层就是调用Unsafe.
                    LockSupport.parkNanos(3000000000l);
                    //or, 在现场中，有个字段叫做parkBlocker，这个api用于设置这个字段
                    LockSupport.parkNanos(this,3000000000l);
                    //or
//                    LockSupport.parkUntil(before + 3000);
                    long after = System.currentTimeMillis();
                    System.out.println("park for " + (after - before) + " millis");
                }
                if(round.get() > 20)return;
            }

        });
        a.start();
        a.join();
    }
    /*
        LockSupport中使用到unsafe的这些调度api
     */
    public void threadApi(Unsafe unsafe) throws InterruptedException {
        AtomicInteger round = new AtomicInteger(0);
        Thread a = new Thread(() -> {
            while (true) {
                System.out.println("hello world " + round.get());
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                round.incrementAndGet();
                if(round.get() % 3 == 0){
                    System.out.println("park");
                    long before = System.currentTimeMillis();
                    /*
                        isAbsolute false表示等待time表示的nano时间。如果isAbsolute true则表示等待直到后面的ms时间戳。
                        0表示永久等待，直到unpark调用
                     */
                    unsafe.park(false, 3000000000l);
                    //or 等待直到before+3s这个时间戳，注意absolute设置为true
                    unsafe.park(true, before + 3000);
                    long after = System.currentTimeMillis();
                    System.out.println("park for " + (after - before) + " millis");
                }
                if(round.get() > 20)return;
            }

        });
        a.start();
        a.join();
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InterruptedException {
        ThreadUsage t = new ThreadUsage();
        t.threadApi(UnsafeUsage.getUnsafe());
    }
}
