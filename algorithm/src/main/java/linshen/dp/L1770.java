package linshen.dp;
/*
给你两个长度分别 n 和 m 的整数数组 nums 和 multipliers ，其中 n >= m ，数组下标 从 1 开始 计数。

初始时，你的分数为 0 。你需要执行恰好 m 步操作。在第 i 步操作（从 1 开始 计数）中，需要：

选择数组 nums 开头处或者末尾处 的整数 x 。
你获得 multipliers[i] * x 分，并累加到你的分数中。
将 x 从数组 nums 中移除。
在执行 m 步操作后，返回 最大 分数。

n == nums.length
m == multipliers.length
1 <= m <= 103
m <= n <= 105
-1000 <= nums[i], multipliers[i] <= 1000
 */

public class L1770 {
    public static void main(String[] args) {
        System.out.println(new L1770().maximumScore(new int[]{2, 3}, new int[]{2, 1}));
    }

    public int maximumScore(int[] nums, int[] multipliers) {
        int n = nums.length;
        int m = multipliers.length;
        long[][] dp = new long[m][n];
        for (int s = m - 1; s >= 0; s--) {
            int len = n - s;
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == n - m + 1) {
                    dp[s][i] = Math.max((long) multipliers[n - len] * nums[i], (long) multipliers[n - len] * nums[j]);
                } else {
                    dp[s][i] = Math.max((long) multipliers[n - len] * nums[i] + dp[s + 1][i + 1], (long) multipliers[n - len] * nums[j] + dp[s + 1][i]);
                }
            }
        }
        return (int) (dp[0][0]);
    }
}
