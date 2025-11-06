package linshen.dp;

/*
给你一个字符串 s ，每一次操作你都可以在字符串的任意位置插入任意字符。

请你返回让 s 成为回文串的 最少操作次数 。

「回文串」是正读和反读都相同的字符串。
 */

public class L1312 {
    public int minInsertions(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == 1) dp[i][j] = 0;
                else if (len == 2) {
                    if (s.charAt(i) == s.charAt(j)) dp[i][j] = 0;
                    else dp[i][j] = 1;
                } else {
                    dp[i][j] = len;
                    if (s.charAt(i) == s.charAt(j)) dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
                    dp[i][j] = Math.min(dp[i][j], 1 + dp[i + 1][j]);
                    dp[i][j] = Math.min(dp[i][j], 1 + dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
