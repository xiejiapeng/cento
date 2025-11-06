package linshen.dp;

/*
给你两个字符串 s 和 t ，统计并返回在 s 的 子序列 中 t 出现的个数。
测试用例保证结果在 32 位有符号整数范围内。
 */

public class L115 {
    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n+1][m+1];
        for (int i = n; i > -1; i--){
            for (int j = m; j > -1; j--){
                if(i == n || j == m) {
                    if(j == m)dp[i][j] = 1;
                } else {
                    if(s.charAt(i) == t.charAt(j)) {
                        dp[i][j] += dp[i+1][j+1];
                    }
                    dp[i][j] += dp[i+1][j];
                }

            }
        }

        return dp[0][0];
    }
}
