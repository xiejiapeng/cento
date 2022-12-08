package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
    只能代理实现了接口的类，或者直接代理一个接口
 */
public class JDKProxy {
    interface Say {
        public void sayHello(String name);
    }
    class PSay implements Say {
        public void sayHello(String name){
            System.out.println("hey " + name);
        }
    }

    class PSayInvocationHandler implements InvocationHandler {
        private PSay pSay;

        public PSayInvocationHandler(PSay pSay) {
            this.pSay = pSay;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("before call");
            Object object = method.invoke(pSay, args);
            System.out.println("after call");
            new Exception().printStackTrace();
            return object;
        }
    }

    public void demo(){
        PSay pSay = new PSay();
        PSayInvocationHandler pSayInvocationHandler = new PSayInvocationHandler(pSay);
        Say proxy = (Say)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{Say.class}, pSayInvocationHandler);
        proxy.sayHello("judy");
    }

    public static void main(String[] args) {
        JDKProxy j = new JDKProxy();
        j.demo();
    }
}
