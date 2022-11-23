package reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectPerformance {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        long start = System.currentTimeMillis();
        Dog dog = new Dog("pet");
        Method dm = Dog.class.getMethod("barkAll", Integer.class, Integer.class);
        long sum = 0;
        long count = 0;

        for (long i = 0; i < 5_000_000_000l; i++){
            if(i % 1_000_000_00 == 0) {
                long cur = System.currentTimeMillis();
                sum += (cur - start);
                count += 1;
//                System.out.println("elapse " + (cur - start) + " millis");
                start = cur;
            }

            dm.invoke(dog, 1,2);
        }

        System.out.println(sum / count);
    }
}
