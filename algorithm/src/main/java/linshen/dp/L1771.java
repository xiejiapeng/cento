package linshen.dp;

/*
给你两个字符串 word1 和 word2 ，请你按下述方法构造一个字符串：

从 word1 中选出某个 非空 子序列 subsequence1 。
从 word2 中选出某个 非空 子序列 subsequence2 。
连接两个子序列 subsequence1 + subsequence2 ，得到字符串。
返回可按上述方法构造的最长 回文串 的 长度 。如果无法构造回文串，返回 0 。

字符串 s 的一个 子序列 是通过从 s 中删除一些（也可能不删除）字符而不更改其余字符的顺序生成的字符串。

回文串 是正着读和反着读结果一致的字符串。
 */

public class L1771 {
    public static void main(String[] args) {
        System.out.println(new L1771().longestPalindrome("aazzlizfmn", "nppqb"));
    }

    public int longestPalindrome(String word1, String word2) {
        String s = word1 + word2;
        int n = s.length();
        int[][] dp = new int[n][n];
        int max = 0;

        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    dp[i][j] = 1;
                } else if (len == 2) {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = 2;
                        if (i < word1.length() && j >= word1.length()) {
                            max = Math.max(max, dp[i][j]);
                        }
                    } else dp[i][j] = 1;
                } else {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = Math.max(dp[i][j], 2 + dp[i + 1][j - 1]);
                        if (i < word1.length() && j >= word1.length()) {
                            max = Math.max(max, dp[i][j]);
                        }
                    }
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j]);
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                }


            }
        }
        return max;
    }
}
