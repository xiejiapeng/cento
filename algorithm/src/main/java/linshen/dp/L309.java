package linshen.dp;

/*
给定一个整数数组 prices，其中第 prices[i] 表示第 i 天的股票价格 。

设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:

卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
提示：

1 <= prices.length <= 5000
0 <= prices[i] <= 1000
 */

public class L309 {
    public static void main(String[] args) {
        System.out.println(new L309().maxProfit(new int[]{6, 1, 6, 4, 3, 0, 2}));
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] dp = new int[n + 3];
        for (int i = n - 1; i > -1; i--) {
            /*
            todo h
            如果dp[i]表示当天必买，那么 dp[i] = Math.max(dp[i], prices[j] - prices[i] + dp[j+2]) 就是错误的，因为dp[j+2]仅能代表j+2天买的情况，而且最终不能返回dp[0]，而是dp的最大值
            所以dp[i]最好表示当天买或者不买，也只需要返回dp[0]
             */
            dp[i] = dp[i + 1];
            for (int j = i; j < n; j++) {
                if (prices[j] >= prices[i]) {
                    dp[i] = Math.max(dp[i], prices[j] - prices[i] + dp[j + 2]);
                }
            }
        }
        return dp[0];
    }
}
