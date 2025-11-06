package linshen.dp;

/*
给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。

计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。

你可以认为每种硬币的数量是无限的。
提示：

1 <= coins.length <= 12
1 <= coins[i] <= 231 - 1
0 <= amount <= 104
 */

public class L322 {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[] dp = new int[amount+1];
        for (int i = 1; i <= amount; i++){
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++){
                if(i >= coins[j] && dp[i-coins[j]] != -1)min = Math.min(min, 1 + dp[i-coins[j]]);
            }
            dp[i] = (min == Integer.MAX_VALUE ? -1 : min);
        }
        return dp[amount];
    }
}
