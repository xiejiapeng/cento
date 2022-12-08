package proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/*
    动态生成被代理类的子类
 */
public class CGLibProxy {
    /*
        如果不是静态内部类，则报错找不到constructor: Superclass has no null constructors but no arguments were given
     */
    static class Sender {
        public Sender(){

        }
        public String sendMessage(String message) {
            System.out.println("send " + message);
            return message;
        }
    }

    public void demo(){
        Sender sender = new Sender();
        MethodInterceptor methodInterceptor = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("before call");
                Object obj = methodProxy.invokeSuper(o, objects);
                System.out.println("after call");
                return obj;
            }
        };

        Enhancer enhancer = new Enhancer();
        enhancer.setClassLoader(Sender.class.getClassLoader());
        enhancer.setSuperclass(Sender.class);
        enhancer.setCallback(methodInterceptor);
        Sender proxy = (Sender) enhancer.create();
        proxy.sendMessage("hey judy");
    }

    public static void main(String[] args) {
        CGLibProxy p = new CGLibProxy();
        p.demo();
    }
}
