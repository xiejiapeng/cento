package linshen.dp;

/*
给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。

子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 */

import java.util.HashMap;
import java.util.Map;

public class L516 {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        int max = 1;
        Map<Character, Integer> first = new HashMap<>();
        for (int i = n - 1; i > -1; i--) {
            if (!first.containsKey(s.charAt(i))) {
                first.put(s.charAt(i), i);
            }
        }
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                dp[i][j] = 1;
                if (len == 1) {
                    dp[i][j] = 1;
                } else if (len == 2) {
                    if (s.charAt(i) == s.charAt(j)) dp[i][j] = 2;
                } else {
                    if (s.charAt(i) == s.charAt(j)) dp[i][j] = Math.max(dp[i][j], 2 + dp[i + 1][j - 1]);
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j]);
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                }

                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }
}
