package linshen.dp;

/*
给你一个字符串 s 和一个整数 k。

在一次操作中，你可以将任意位置的字符替换为字母表中相邻的字符（字母表是循环的，因此 'z' 的下一个字母是 'a'）。例如，将 'a' 替换为下一个字母结果是 'b'，将 'a' 替换为上一个字母结果是 'z'；同样，将 'z' 替换为下一个字母结果是 'a'，替换为上一个字母结果是 'y'。

返回在进行 最多 k 次操作后，s 的 最长回文子序列 的长度。

子序列 是一个 非空 字符串，可以通过删除原字符串中的某些字符（或不删除任何字符）并保持剩余字符的相对顺序得到。

回文 是正着读和反着读都相同的字符串。
 */
public class L3472 {
    public static void main(String[] args) {
//        System.out.println(new L3472().dist('a','c'));
        System.out.println(new L3472().longestPalindromicSubsequence("abced", 2));
    }

    public int longestPalindromicSubsequence(String s, int k) {
        int n = s.length();
        int[][][] dp = new int[n][n][k + 1];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                for (int t = 0; t <= k; t++) {
                    if (len == 1) dp[i][j][t] = 1;
                    else if (len == 2) {
                        if (dist(s.charAt(i), s.charAt(j)) <= t) dp[i][j][t] = 2;
                        else dp[i][j][t] = 1;
                    } else {
                        if (len == 3) {
                            System.out.println("f");
                        }
                        //trans i and j
                        if (dist(s.charAt(i), s.charAt(j)) <= t)
                            dp[i][j][t] = Math.max(dp[i][j][t], 2 + dp[i + 1][j - 1][t - dist(s.charAt(i), s.charAt(j))]);

                        dp[i][j][t] = Math.max(dp[i][j][t], dp[i + 1][j][t]);
                        dp[i][j][t] = Math.max(dp[i][j][t], dp[i][j - 1][t]);
                    }
                }
            }
        }

        return dp[0][n - 1][k];
    }

    private int dist(char a, char b) {
        return Math.min(Math.floorMod((a - b), 26), Math.floorMod((b - a), 26));
    }
}
