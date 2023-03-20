package solutions;

public class B extends A{
    int x = 20;

    static int y = 50;
    public static void sCall(){
        System.out.println("b static call");
    }

    public void vCall(){
        System.out.println("b virtual call");
    }
}
