package linshen.dp;

/*
给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。
一旦你支付此费用，即可选择向上爬一个或者两个台阶。
你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。

请你计算并返回达到楼梯顶部的最低花费
 */

public class L746 {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n];
        dp[n-1] = cost[n-1];
        for (int i = n - 2; i > -1; i--){
            if(i == n - 2) {
                dp[i] = cost[i];
            } else {
                dp[i] = cost[i] + Math.min(dp[i+1], dp[i+2]);
            }
        }
        return Math.min(dp[0],dp[1]);
    }
}
