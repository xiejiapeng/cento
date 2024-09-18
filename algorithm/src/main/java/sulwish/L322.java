package sulwish;

/*
给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。

计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。

你可以认为每种硬币的数量是无限的。
 */

public class L322 {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] f = new int[n][amount+1];
        for (int i = 0; i < n; i++){
            for (int j = 0; j <= amount; j++){
                f[i][j] = Integer.MAX_VALUE;
                if(j == 0){
                    f[i][j] = 0;
                    continue;
                } else if(i == 0){
                    if(j % coins[i] == 0){
                        f[i][j] = j / coins[i];
                    }
                } else {
                    int use = 0;
                    while (use * coins[i] <= j){
                        if(f[i-1][j-use*coins[i]] != Integer.MAX_VALUE) {
                            f[i][j] = Math.min(f[i][j], use + f[i-1][j-use * coins[i]]);
                        }
                        use++;


                    }
                }


            }
        }
        return f[n-1][amount] == Integer.MAX_VALUE ? -1 : f[n-1][amount];
    }
}
