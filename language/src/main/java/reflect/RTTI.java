package reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class RTTI {

    private static void rtti(){
        Student student = new Student();
        student.setName("tom");
        student.setAge(20);
        student.say("world");

        //list all methods of Student
       Method[] methods = student.getClass().getDeclaredMethods();
       Field[] fields = student.getClass().getFields();
       System.out.println(student.find(12));

       student.setPet(new Dog("aa"));
       student.getPet().bark();
    }

    public static void main(String[] args) {
        rtti();
    }
}
