package linshen.dp;

/*
给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
提示：

1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 100
 */

public class L718 {
    public int findLength(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[][] dp = new int[n+1][m+1];
        int max = 0;
        for (int i = n - 1; i > -1; i--){
            for (int j = m - 1; j > -1; j--){
                //todo m 注意子序列和子数组的区别；子集的子序列仍然是父集的子序列，但是子数组未必，子数组一定是连续的；
                // 这种情况下，要固定端点，同时记录每个固定点的最值
                if(nums1[i] == nums2[j]) {
                    dp[i][j] = Math.max(dp[i][j], 1 + dp[i+1][j+1]);
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }
}
