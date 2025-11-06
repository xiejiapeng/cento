package linshen.dp;

/*
给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，长度均为 n 。
让我们定义另一个下标从 0 开始、长度为 n 的整数数组，nums3 。对于范围 [0, n - 1] 的每个下标 i ，你可以将 nums1[i] 或 nums2[i] 的值赋给 nums3[i] 。
你的任务是使用最优策略为 nums3 赋值，以最大化 nums3 中 最长非递减子数组 的长度。
以整数形式表示并返回 nums3 中 最长非递减 子数组的长度。

注意：子数组 是数组中的一个连续非空元素序列。
提示：

1 <= nums1.length == nums2.length == n <= 105
1 <= nums1[i], nums2[i] <= 109
 */

public class L2771 {
    public int maxNonDecreasingLength(int[] nums1, int[] nums2) {
        int n = nums1.length;
        //0 means from 1, 1 means from 2
        int[][] dp = new int[n][2];
        dp[n-1][0] = 1;
        dp[n-1][1] = 1;
        int max = 1;
        for (int i = n - 2; i > -1; i--) {
            dp[i][0] = 1;
            dp[i][1] = 1;
            //from 1
            if(nums1[i+1] >= nums1[i]) {
                dp[i][0] = Math.max(dp[i][0], 1 + dp[i+1][0]);
            }
            if(nums2[i+1] >= nums1[i]) {
                dp[i][0] = Math.max(dp[i][0], 1 + dp[i+1][1]);
            }
            //from 0
            if(nums1[i+1] >= nums2[i]) {
                dp[i][1] = Math.max(dp[i][1], 1 + dp[i+1][0]);
            }
            if(nums2[i+1] >= nums2[i]) {
                dp[i][1] = Math.max(dp[i][1], 1 + dp[i+1][1]);
            }
            max = Math.max(dp[i][0], max);
            max = Math.max(dp[i][1], max);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new L2771().maxNonDecreasingLength(new int[]{1,1}, new int[]{2,2}));
    }
}
