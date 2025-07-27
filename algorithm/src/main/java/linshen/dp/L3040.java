package linshen.dp;

/*
给你一个整数数组 nums ，如果 nums 至少 包含 2 个元素，你可以执行以下操作中的 任意 一个：

选择 nums 中最前面两个元素并且删除它们。
选择 nums 中最后两个元素并且删除它们。
选择 nums 中第一个和最后一个元素并且删除它们。
一次操作的 分数 是被删除元素的和。

在确保 所有操作分数相同 的前提下，请你求出 最多 能进行多少次操作。

请你返回按照上述要求 最多 可以进行的操作次数。
 */

public class L3040 {
    public int maxOperations(int[] nums) {
        int n = nums.length;
        int x = maxOperations(nums, nums[0] + nums[1]);
        int y = maxOperations(nums, nums[n - 1] + nums[n - 2]);
        int z = maxOperations(nums, nums[0] + nums[n - 1]);
        return Math.max(x, Math.max(y, z));
    }

    public int maxOperations(int[] nums, int score) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    //todo m 就剩一个，不能构成操作
//                    if(nums[i] == score)dp[i][j] = 1;
                } else if (len == 2) {
                    if (nums[i] + nums[j] == score) dp[i][j] = 1;
                } else {
                    //todo m 注意 是 1+dp[][]
                    //first two
                    if (nums[i] + nums[i + 1] == score) dp[i][j] = Math.max(dp[i][j], 1 + dp[i + 2][j]);
                    //last two
                    if (nums[j] + nums[j - 1] == score) dp[i][j] = Math.max(dp[i][j], 1 + dp[i][j - 2]);
                    //first and last one
                    if (nums[i] + nums[j] == score) dp[i][j] = Math.max(dp[i][j], 1 + dp[i + 1][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
