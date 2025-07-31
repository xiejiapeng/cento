package linshen.dp;

/*
给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 */

public class L96 {
    public int numTrees(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 <= n; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    dp[i][j] = 1;
                } else if (len == 2) {
                    dp[i][j] = 2;
                } else {
                    for (int t = i; t <= j; t++) {
                        if (t == i) dp[i][j] += dp[i + 1][j];
                        else if (t == j) dp[i][j] += dp[i][j - 1];
                        else {
                            dp[i][j] += dp[i][t - 1] * dp[t + 1][j];
                        }
                    }
                }
            }
        }

        return dp[1][n];
    }
}
