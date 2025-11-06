package linshen.dp;

/*
给定两个单词 word1 和 word2 ，返回使得 word1 和 word2 相同所需的最小步数。
每步 可以删除任意一个字符串中的一个字符。
 */

public class L583 {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n+1][m+1];
        for (int i = n; i > -1; i--) {
            for (int j = m; j > -1; j--) {
                dp[i][j] = (n-i)+(m-j);
                if(i == n && j == m)dp[i][j] = 0;
                else if(i==n) {
                    dp[i][j] = m - j;
                } else if(j == m) {
                    dp[i][j] = n - i;
                } else {
                    if(word1.charAt(i) == word2.charAt(j)) {
                        dp[i][j] = dp[i+1][j+1];
                    }
                    dp[i][j] = Math.min(dp[i][j],1 +  dp[i+1][j]);
                    dp[i][j] = Math.min(dp[i][j], 1 + dp[i][j+1]);
                }

            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        System.out.println(new L583().minDistance("a","b"));
    }
}
