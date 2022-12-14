package unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class ObjectUsage {
    public void getFieldOffset (Unsafe unsafe) throws NoSuchFieldException {
        Field nameField = A.class.getDeclaredField("name");
        long nameOffset = unsafe.objectFieldOffset(nameField);
        System.out.println("name offset " + nameOffset);

        //为了8字节对齐，age虽然声明在后面，但其实对象的布局中age放在前面
        Field ageField = A.class.getDeclaredField("age");
        long ageOffset = unsafe.objectFieldOffset(ageField);
        System.out.println("age offset " + ageOffset);

        Field addressField = A.class.getDeclaredField("address");
        long addressOffset = unsafe.objectFieldOffset(addressField);
        System.out.println("address offset " + addressOffset);

        //父类的field不能使用getDeclaredField的方式拿到
        System.out.println("grade offset " + unsafe.objectFieldOffset(A.class.getField("grade")));
        //父类的field总是在子类的field的前面
        System.out.println("parent address offset " + unsafe.objectFieldOffset(A.class.getField("pAdd")));
    }

    public void setField(Unsafe unsafe) throws NoSuchFieldException {
        A a = new A("alice",18,new Address(), 10, new Address());
        System.out.println("before " + a.name);
        long nameOffset = unsafe.objectFieldOffset(A.class.getDeclaredField("name"));
        unsafe.putObject(a, nameOffset, "bob");
        System.out.println("after " + a.name);

        //age具有private属性，但是也可以设置
        /*
            还有一种volatile用法，即putIntVolatile，这样修改能对其他线程立即可见，但是这个field首先必须是volatile的!!!
            还有一种ordered用法，即putOrderedInt，它保证了有序性
            ordered加入的是storestore屏障，防止了store之间的重排序，但是load可以重排序；volatile加入的storeload屏障，store之后的都不能重排序到store之前。
         */
        System.out.println("before " + a.getAge());
        long ageOffset = unsafe.objectFieldOffset(A.class.getDeclaredField("age"));
        unsafe.putInt(a, ageOffset, 20);
        unsafe.putIntVolatile(a,ageOffset,20);
        unsafe.putOrderedInt(a,ageOffset,20);
        System.out.println("after " + a.getAge());
    }

    public void createObject(Unsafe unsafe) throws InstantiationException {
        //A未提供午无参数的构造方法，所以不能这样调用
//        A a = new A();

        //allocateInstance构造出没有初始化过的对象，不能通过类的构造方法创建对象；或者即使构造器是private的，也可以构造出对象来
        A a = (A)unsafe.allocateInstance(A.class);
        System.out.println(a.name);
    }

    public void arrayOffset(Unsafe unsafe) {
        System.out.println("int[] first value offset " + unsafe.arrayBaseOffset(int[].class));
        System.out.println("int[] element size " + unsafe.arrayIndexScale(int[].class));

        System.out.println("A[] first value offset " + unsafe.arrayBaseOffset(A[].class));
        System.out.println("A[] element size " + unsafe.arrayIndexScale(A[].class));

    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Unsafe unsafe = UnsafeUsage.getUnsafe();
        ObjectUsage o = new ObjectUsage();
        o.getFieldOffset(unsafe);
        System.out.println("--------------------");
        o.setField(unsafe);
        System.out.println("--------------------");
        o.createObject(unsafe);
        System.out.println("--------------------");
        o.arrayOffset(unsafe);
    }
}
