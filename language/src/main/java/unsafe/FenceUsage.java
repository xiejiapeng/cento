package unsafe;

import lombok.SneakyThrows;
import sun.misc.Unsafe;

public class FenceUsage {
    private boolean flag = false;
    private int a = 10;

    class Sig implements Runnable{
        FenceUsage fenceUsage;

        public Sig(FenceUsage fenceUsage){
            this.fenceUsage = fenceUsage;
        }

        @SneakyThrows
        public void run(){
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " sig ok");
            fenceUsage.a = 100;
            fenceUsage.flag = true;
        }
    }

    class Con implements Runnable{
        FenceUsage fenceUsage;
        public Con(FenceUsage fenceUsage) {
            this.fenceUsage = fenceUsage;
        }

        public void run(){
            Unsafe unsafe = Unsafe.getUnsafe();
            while (true) {
                System.out.println(Thread.currentThread().getName() + " check");
                if(fenceUsage.flag) {
                    unsafe.loadFence();
                    System.out.println(Thread.currentThread().getName() + " con ok");
                    System.out.println(fenceUsage.a);
                    break;
                }
            }
        }
    }

    @SneakyThrows
    public void noFence(){
        FenceUsage fenceUsage = new FenceUsage();
        Sig sig = new Sig(fenceUsage);
        Con con = new Con(fenceUsage);
        for (int i = 0; i < 100; i++){
            Thread st = new Thread(sig);
            Thread ct = new Thread(con);
            st.start();
            ct.start();
            ct.join();
        }

    }

    public static void main(String[] args) {
        FenceUsage f = new FenceUsage();
        f.noFence();
    }

    public void fenceWithVolatile(){

    }
}
