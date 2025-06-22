package linshen.dp;

/*
给你一个整数数组 arr，请你将该数组分隔为长度 最多 为 k 的一些（连续）子数组。分隔完成后，
每个子数组的中的所有值都会变为该子数组中的最大值。

返回将数组分隔变换后能够得到的元素最大和。本题所用到的测试用例会确保答案是一个 32 位整数。
提示：

1 <= arr.length <= 500
0 <= arr[i] <= 109
1 <= k <= arr.length
 */

public class L1043 {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n+1];
        for (int i = n - 1; i > -1; i--) {
            int m = arr[i];
            for (int j = i; j - i + 1 <= k && j < n; j++) {
                m = Math.max(m, arr[j]);
                dp[i] = Math.max(dp[i], m * (j-i+1) + dp[j+1]);
            }
        }
        return dp[0];
    }

}
