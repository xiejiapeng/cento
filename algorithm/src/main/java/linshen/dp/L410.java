package linshen.dp;

/*
给定一个非负整数数组 nums 和一个整数 k ，你需要将这个数组分成 k 个非空的连续子数组，使得这 k 个子数组各自和的最大值 最小。
返回分割后最小的和的最大值。

子数组 是数组中连续的部份。
提示：

1 <= nums.length <= 1000
0 <= nums[i] <= 106
1 <= k <= min(50, nums.length)
 */

public class L410 {
    public int splitArray(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n+1];
        for (int i = 0; i < n; i++){
            sum[i+1] = sum[i] + nums[i];
        }
        int[][] dp = new int[n+1][k+1];
        for (int j = 1; j <= k; j++){
            for (int i = n - 1; i > -1; i--) {
                if(j == 1)dp[i][j] = sum[n] - sum[i];
                else {
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int t = i; t < n; t++) {
                        dp[i][j] = Math.min(dp[i][j], Math.max(dp[t+1][j-1], sum[t+1]-sum[i]));
                    }
                }
            }
        }
        return dp[0][k];
    }
}
