package linshen.dp;

/*
给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。

请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。

假设每一种面额的硬币有无限个。

题目数据保证结果符合 32 位带符号整数。

提示：

1 <= coins.length <= 300
1 <= coins[i] <= 5000
coins 中的所有值 互不相同
0 <= amount <= 5000
 */

public class L518 {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][amount+1];
        for (int i = 0; i < n; i++){
            for (int j = 0; j <= amount; j++){
                if(j == 0)dp[i][j] = 1;
                else if(i == 0){
                    if(j % coins[i] == 0)dp[i][j] = 1;
                    else dp[i][j] = 0;
                } else {
                    if(i == 2 && j == 5) {
                        System.out.println("f");
                    }
                    int m = j / coins[i];
                    for (int t = 0; t <= m; t++){
                        //todo m 注意这里*coins[i]，不要犯低级错误
                        dp[i][j] += dp[i-1][j - t * coins[i]];
                    }
                }

            }
        }
        return dp[n-1][amount];
    }

    public static void main(String[] args) {
        System.out.println(new L518().change(5, new int[]{1,2,5}));
    }
}
