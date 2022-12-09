package unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

public class UnsafeUsage {
    public static Unsafe getUnsafe() throws NoSuchFieldException, IllegalAccessException {
        // not allowed to invoke this method unless the caller class's classloader is system classloader
//        Unsafe u = Unsafe.getUnsafe();
        Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
        unsafeField.setAccessible(true);
        return (Unsafe) unsafeField.get(null);
    }

    public static void memoryTest(Unsafe unsafe) {
        int size = 4;
        long addr1 = unsafe.allocateMemory(size);
        long addr2 = unsafe.reallocateMemory(addr1, size * 2);
        /*
            this is a virtual memory address consisted of 48 bits. So the hex string
            of the address is xx xx xx xx xx xx
         */
        System.out.println("binary address " + Long.toHexString(addr1));
        //todo why addr1 equals to addr2
        System.out.println("addr1 " + addr1);
        System.out.println("addr2 " + addr2);
        try {
            unsafe.setMemory(null,addr1, size, (byte) 1);
            unsafe.copyMemory(null, addr1, null, addr2, size);
            unsafe.copyMemory(null, addr1, null, addr2 + size, size);
            System.out.println("addr1 value " + unsafe.getInt(addr1));
            System.out.println("addr2 value " + unsafe.getInt(addr2));
        } finally {
            unsafe.freeMemory(addr1);
            //addr1 equals to addr2, so may cause double free warning.
//            unsafe.freeMemory(addr2);
            //after been freed, memory value is not defined.
            System.out.println("after free addr1 value " + unsafe.getInt(addr1));
            System.out.println("after free addr2 value " + unsafe.getInt(addr2));
        }

    }

    public static void BufferTest(){
        ByteBuffer byteBuffer;
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Unsafe u = getUnsafe();
        memoryTest(u);
    }
}
