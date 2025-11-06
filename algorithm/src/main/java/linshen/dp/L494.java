package linshen.dp;

/*
给你一个非负整数数组 nums 和一个整数 target 。

向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：

例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。

提示：

1 <= nums.length <= 20
0 <= nums[i] <= 1000
0 <= sum(nums[i]) <= 1000
-1000 <= target <= 1000
 */

public class L494 {
    //sum is i-20000
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int[][] dp = new int[n+1][40001];
        for (int i = 1; i <= n; i++){
            for (int j = 0; j <= 40000; j++){
                int s = j - 20000;
                if(i == 1) {
                    if(s == nums[i-1])dp[i][j] += 1;
                    if(s == -nums[i-1])dp[i][j] += 1;
                } else {
                    if(j-nums[i-1]>=0)dp[i][j] += dp[i-1][j-nums[i-1]];
                    if(j+nums[i-1]<=40000)dp[i][j] += dp[i-1][j+nums[i-1]];
                }
            }
        }
        return dp[n][target + 20000];
    }
}
