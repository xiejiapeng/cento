package linshen.dp;
/*
给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文串。
返回符合要求的 最少分割次数 。
提示：
1 <= s.length <= 2000
s 仅由小写英文字母组成
 */
public class L132 {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if(len == 1)dp[i][j] = true;
                else if(len == 2)dp[i][j] = (s.charAt(i) == s.charAt(j));
                else {
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i+1][j-1]);
                }
            }
        }

        int[] f = new int[n+1];
        for (int i = n - 1; i > -1; i--){
            f[i] = n - i - 1;
            if(dp[i][n-1])f[i] = 0;
            for (int j = i; j < n; j++){
                if(dp[i][j]) {
                    f[i] = Math.min(f[i], 1 + f[j+1]);
                }
            }
        }
        return f[0];
    }
}
