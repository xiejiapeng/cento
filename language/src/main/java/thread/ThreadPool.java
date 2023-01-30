package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPool {
    private AtomicInteger poolNum = new AtomicInteger();
    private ThreadPoolExecutor productPool = new ThreadPoolExecutor(1, 1, 100, TimeUnit.MINUTES, new ArrayBlockingQueue<>(1), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            String name = "myThread-" + poolNum.incrementAndGet();
            return new Thread(name);
        }
    }, (r, executor) -> System.out.println("reject this"));

    public void run() throws InterruptedException {
        Thread.sleep(2000);
        while (true){
            Thread a = new Thread(()->{
                List<Future<Integer>> futures = new ArrayList<>();
                for (int i = 0; i < 5; i++){
                    Future<Integer> f = productPool.submit(() -> 10);
                    futures.add(f);
                }

                int sum = 0;
                for (Future<Integer> f : futures){
                    try {
                        int x = f.get();
                        sum += x;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("sum="+sum);
            });
            a.start();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        ThreadPool t = new ThreadPool();
        t.run();
    }
}
