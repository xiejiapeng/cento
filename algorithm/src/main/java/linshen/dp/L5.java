package linshen.dp;

/*
给你一个字符串 s，找到 s 中最长的 回文 子串。
 */

public class L5 {
    public String longestPalindrome(String s) {
        int n = s.length();
        int max = 1;
        String ms = "";
        boolean[][] dp = new boolean[n][n];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    dp[i][j] = true;
                } else if (len == 2) {
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    dp[i][j] = (s.charAt(i) == s.charAt(j)) && dp[i + 1][j - 1];
                }

                if (dp[i][j]) {
                    if (len >= max) {
                        max = len;
                        ms = s.substring(i, j + 1);
                    }
                }
            }
        }
        return ms;
    }
}
