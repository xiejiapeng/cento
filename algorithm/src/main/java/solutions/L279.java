package solutions;

public class L279 {
    public int numSquares(int n) {
        int[] c = new int[n+1];
        c[0] = 0;
        c[1] = 1;
        for(int i = 2; i <= n; i++){
            c[i] = Integer.MAX_VALUE;
            for(int j = 1; j * j <= i; j++){
                c[i] = Math.min(c[i],1 + c[i-j*j]);
            }
        }
        return c[n];
    }

    public static void main(String[] args) {
        //12=8+4
        System.out.println(new L279().numSquares(19));
    }
}
