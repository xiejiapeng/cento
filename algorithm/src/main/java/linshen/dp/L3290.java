package linshen.dp;

/*
给你一个大小为 4 的整数数组 a 和一个大小 至少为 4 的整数数组 b。
你需要从数组 b 中选择四个下标 i0, i1, i2, 和 i3，并满足 i0 < i1 < i2 < i3。
你的得分将是 a[0] * b[i0] + a[1] * b[i1] + a[2] * b[i2] + a[3] * b[i3] 的值。

返回你能够获得的 最大 得分。

提示：
a.length == 4
4 <= b.length <= 105
-105 <= a[i], b[i] <= 105
 */

public class L3290 {
    public long maxScore(int[] a, int[] b) {
        int n = a.length;
        int m = b.length;
        long[][] dp = new long[n+1][m+1];
        for (int i = n-1; i > -1; i--){
            for (int j = m-1; j > -1; j--) {
                if(i == 2 && j == 2){
                    System.out.println("f");
                }
                if(m-j==n-i) {
                    long s = 0;
                    for (int t = i; t < n; t++){
                        s += (long)(a[t])*(long)(b[t-i+j]);
                    }
                    System.out.println("i="+i+",j="+j+"s="+s);
                    dp[i][j] = s;
                } else if(m-j<n-i) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Long.MIN_VALUE;
                    dp[i][j] = Math.max(dp[i][j], dp[i][j+1]);
                    dp[i][j] = Math.max(dp[i][j], (long)a[i] * (long)b[j] + dp[i+1][j+1]);
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        System.out.println(new L3290().maxScore(new int[]{-1,4,5,-2}, new int[]{-5,-1,-3,-2,-4}));
    }
}
