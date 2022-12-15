package collection.list;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class ListUsage {
    public void array() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList<String> a = new ArrayList<>();
        a.add("abc");
        a.add("def");
        //泛型报错，在编译的时候还是有类型信息的
//        a.add(10);

        Method m = ArrayList.class.getMethod("add", new Class[]{Object.class});
        m.setAccessible(true);
        m.invoke(a, "xyz");
        System.out.println("after invoke " + a.get(2));

        //绕过bypass编译报错，但是获取元素和toarray的时候会cast出错
//        m.invoke(a, 123);
//        System.out.println("after bypass, size is " + a.size());
//        System.out.println("after invoke " + a.get(3));

        /*
            arraylist内部用array保存数据，因为无法构建泛型数组，所以内部使用的array是Object[]类型，这里
            返回的是对内部数组的copy。
         */
        Object[] objects = a.toArray();
        /*
            这种带有类型的toArray使用的是系统的System.arraycopy方法，它底层实现了对象的拷贝，不需要在编译
            层面关注类型安全的问题

            但是要注意在toArray实现中，如果输入的数组长度小于size，要扩大数组长度。
            这里就要用到基于传入数组的类型构建一个新数组 (T[]) Arrays.copyOf(elementData, size, a.getClass());
            要传入数组的类型的原因为了构建新数组，所构建的新数组的元素类型就是a.getClass指定的类型，但是方法返回的是Object，
            因为在编译期间不知道a.getClass到底是什么。最后为了避免编译报错，对返回的Object进行强制类型转换，变成(T[])类型，
            这样就避免了编译问题。
         */
        String[] s = a.toArray(new String[0]);
        System.out.println("internal object array[0] " + objects[0]);
        System.out.println("actual internal element type" + objects[0] instanceof String);
        System.out.println("to generic array[0] "+s[0]);

        System.out.println(a.get(1));
        for (String x : a){
            if(x.equalsIgnoreCase("def"))a.remove(x);
        }
        System.out.println(a.get(1));

        /*
            这里再加上uuu的原因是，经过前面代码运行，a中只剩下abc,xyz两个元素。本意是想复现ConcurrentModificationException
            这个异常。如果通过remove(abc)本来是会造成ConcurrentModificationException的，但是这个异常是在next中检验，而删除abc
            以后a的hasNext就返回了false（位置发生错乱），所以没有成功ConcurrentModificationException异常

            增加uuu这个元素，能够让iterator还有元素，所以执行next了，所以复现了异常


         */
        a.add("uuu");
        Iterator<String> i = a.iterator();
        while (i.hasNext()) {
            String x = i.next();
            System.out.println(x);
            if(x.equalsIgnoreCase("abc")) {
                //在原对象上remove会出现ConcurrentModificationException
//                a.remove(x);

                /*
                    用iterator自身的remove不会出现ConcurrentModificationException异常，它会重置expectModCount = modCount
                    这个异常的本意就是不想iterator在迭代的时候发生错误，所以不需要避免iterator自己修改数据的情况。
                 */
                i.remove();
            }
        }
        System.out.println(a.get(0));

    }

    public void link(){
        /*
            linkedlist一般写算法题的时候会用到，可以当做双端队列，栈使用。linkedlist内部保存的是一个双向链表

            linkedlist在时间复杂度方面，增删比array快，但是随机访问比array慢。要想删除指定位置的元素，linkedlist还是会很慢
            linkedlist最大的劣势还是缓存不友好，array内部使用的是数组，遍历的时候能快速拿到同一cacheline的元素。
            当然了，同一cacheline的元素的更新又会出现伪分享的问题。
         */
        LinkedList<String> l = new LinkedList<>();
    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        ListUsage l = new ListUsage();
        l.array();
    }
}
