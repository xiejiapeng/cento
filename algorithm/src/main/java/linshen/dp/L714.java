package linshen.dp;

/*
给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。

你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。

返回获得利润的最大值。

注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。

提示：

1 <= prices.length <= 5 * 104
1 <= prices[i] < 5 * 104
0 <= fee < 5 * 104
 */

public class L714 {
    public static void main(String[] args) {
        System.out.println(new L714().maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));
    }

    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        if (n == 0) return 0;
        //buy at i
        int[] dp = new int[n];
        int max = 0;
        int m = 0;
        for (int i = n - 2; i > -1; i--) {
            //todo h
            if (i == n - 2) {
                dp[i] = Math.max(-fee, (prices[i + 1] - prices[i] - fee));
            } else {
                //记住，已经限定了dp为当天必买，就应该用-fee+max，而不能用-fee
                dp[i] = Math.max(-fee + max, dp[i + 1] + (prices[i + 1] - prices[i]));
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
