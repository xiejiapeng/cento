package linshen.dp;

/*
给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。

注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

提示：

1 <= prices.length <= 105
0 <= prices[i] <= 105

todo h
 */

import java.util.Arrays;

public class L123 {
    public static void main(String[] args) {
        System.out.println(new L123().maxProfit(new int[]{2, 1, 2, 0, 1}));
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;
        //buy at i, sell once
        int[] a = new int[n];
        int[] s = new int[n + 1];
        int max = prices[n - 1];
        for (int i = n - 1; i > -1; i--) {
            a[i] = Math.max(0, max - prices[i]);
            max = Math.max(max, prices[i]);
            s[i] = Math.max(s[i + 1], a[i]);
        }

        //buy at i, sell less than two times
        int[] b = new int[n];
        for (int i = n - 2; i > -1; i--) {
            //buy at i, sell at i
            b[i] = s[i];

            //buy at i, don't sell at i+1
            b[i] = Math.max(b[i], b[i + 1] - (prices[i] - prices[i + 1]));
        }

        return Arrays.stream(b).max().getAsInt();
    }
}
