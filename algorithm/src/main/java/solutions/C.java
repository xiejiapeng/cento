package solutions;

public class C {
    public static void main(String[] args) {
        A a = new B();
        System.out.println(a.x);
        System.out.println(A.y);
        System.out.println(B.y);
        a.vCall();
        A.sCall();
        B.sCall();
    }
}
