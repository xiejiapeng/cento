package linshen.dp;

/*
我们正在玩一个猜数游戏，游戏规则如下：

我从 1 到 n 之间选择一个数字。
你来猜我选了哪个数字。
如果你猜到正确的数字，就会 赢得游戏 。
如果你猜错了，那么我会告诉你，我选的数字比你的 更大或者更小 ，并且你需要继续猜数。
每当你猜了数字 x 并且猜错了的时候，你需要支付金额为 x 的现金。如果你花光了钱，就会 输掉游戏 。
给你一个特定的数字 n ，返回能够 确保你获胜 的最小现金数，不管我选择那个数字 。
 */

public class L375 {
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int len = 1; len <= n + 1; len++) {
            for (int i = 0; i + len - 1 <= n; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    dp[i][j] = 0;
                } else if (len == 2) {
                    dp[i][j] = Math.min(i, j);
                } else {
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int t = i; t <= j; t++) {
                        dp[i][j] = Math.min(dp[i][j], t + Math.max(t - 1 >= i ? dp[i][t - 1] : 0, t + 1 <= j ? dp[t + 1][j] : 0));
                    }
                }
            }
        }
        return dp[1][n];
    }
}
