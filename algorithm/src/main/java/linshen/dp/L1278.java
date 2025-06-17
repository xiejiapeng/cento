package linshen.dp;

/*
给你一个由小写字母组成的字符串 s，和一个整数 k。

请你按下面的要求分割字符串：

首先，你可以将 s 中的部分字符修改为其他的小写英文字母。
接着，你需要把 s 分割成 k 个非空且不相交的子串，并且每个子串都是回文串。
请返回以这种方式分割字符串所需修改的最少字符数。
提示：

1 <= k <= s.length <= 100
s 中只含有小写英文字母。
 */

public class L1278 {
    public int palindromePartition(String s, int k) {
        int n = s.length();

        int[][] f = new int[n][n];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if(len == 1)f[i][j] = 0;
                else if(len == 2) {
                    f[i][j] = (s.charAt(i) == s.charAt(j)) ? 0 : 1;
                } else {
                    f[i][j] = ((s.charAt(i) == s.charAt(j)) ? 0 : 1) + (f[i+1][j-1]);
                }
            }
        }

        int[][] dp = new int[n+1][k+1];
        for (int t = 1; t <= k; t++) {
            for (int i = n - 1; i > -1; i--) {
                if(t == 1){
                    dp[i][t] = f[i][n-1];
                } else {
                    dp[i][t] = Integer.MAX_VALUE;
                    //todo m 注意这里的判断条件
                    for (int j = i; (n-j >= t); j++) {
                        dp[i][t] = Math.min(dp[i][t], f[i][j] + dp[j+1][t-1]);
                    }
                }

            }
        }
        return dp[0][k];
    }

    public static void main(String[] args) {
        System.out.println(new L1278().palindromePartition("aea", 2));
    }
}
