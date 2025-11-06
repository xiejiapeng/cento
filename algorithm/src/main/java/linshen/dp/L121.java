package linshen.dp;

/*
给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。
设计一个算法来计算你所能获取的最大利润。
返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
7 1 5 3 6 4
 */

import java.util.Arrays;

public class L121 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] f = new int[n];
        f[n-1] = 0;
        int max = 0;
        for (int i = n - 2; i > -1; i--){
            //buy at i
            int a = Math.max(0, prices[i+1]-prices[i]);
            int b = Math.max(0, f[i+1] + (prices[i+1]-prices[i]));
            f[i] = Math.max(a, b);
            max = Math.max(max, f[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new L121().maxProfit(new int[]{7,1,5,3,6,4}));
    }
}
