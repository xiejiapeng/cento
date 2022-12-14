package unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

public class CASUsage {
    public void cas(Unsafe unsafe) throws NoSuchFieldException {
        Address address = new Address();
        A a = new A("alice",18, address, 10, address);
        System.out.println("before " + a.getAge());
        Field age = A.class.getDeclaredField("age");
        long offset = unsafe.objectFieldOffset(age);
        unsafe.compareAndSwapInt(a, offset, 18, 25);
        System.out.println("after " + a.getAge());

        long addOffset = unsafe.objectFieldOffset(A.class.getDeclaredField("address"));
        Address a2 = new Address();
        a2.country = "china";

        Address a3 = new Address();
        //这样可以成功，因为比较的是同一个对象
        System.out.println("success? " + unsafe.compareAndSwapObject(a, addOffset, address, a2));
        //这样不成功，因为比较的不是同一个对象
        System.out.println("success? " + unsafe.compareAndSwapObject(a, addOffset, a3, a2));

        long nameOffset = unsafe.objectFieldOffset(A.class.getDeclaredField("name"));
        //这样不成功，虽然对象值一样，但是不是同一个对象
        System.out.println("success? " + unsafe.compareAndSwapObject(a, nameOffset, new String("alice"), "bob"));
        //成功
        System.out.println("success? " + unsafe.compareAndSwapObject(a, nameOffset, "alice", "bob"));


        //一般可以用这种自旋的方式实现复杂的操作
        System.out.println("before age " + a.getAge());
        boolean success;
        do{
            int nowAge = a.getAge();
            int expected = nowAge + 1;
            success = unsafe.compareAndSwapInt(a, offset, nowAge, expected);
        } while (!success);
        System.out.println("after age " + a.getAge());

        //unsafe本身也提供了getAndAddInt实现原子增加，底层也是cas
        unsafe.getAndAddInt(a, offset, 5);

        System.out.println("after age " + a.getAge());
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Unsafe unsafe = UnsafeUsage.getUnsafe();
        CASUsage c = new CASUsage();
        c.cas(unsafe);
    }
}
