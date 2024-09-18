package sulwish;

/*
给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。

完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 */

import java.util.TreeSet;

public class L279 {
    public int numSquares(int n) {
        TreeSet<Integer> all = new TreeSet<>();
        int i = 0;
        while (i * i <= n) {
            all.add(i * i);
            i++;
        }
        System.out.println(all);
        int[] dp = new int[n+1];
        dp[0] = 1;
        for (int j = 1; j <= n; j++){
            dp[j] = j;
            for(int x : all){
                if(x == j)dp[j] = 1;
                else {
                    if(j > x)dp[j] = Math.min(dp[j], 1 + dp[j-x]);
                }
            }
        }
        return dp[n];
    }
    public static void main(String[] args) {
        System.out.println(new L279().numSquares(12));
    }
}
