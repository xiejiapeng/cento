package linshen.dp;

/*
给你一个长度为 n 的整数数组 nums。

子数组 nums[l..r]（其中 0 <= l <= r < n）的 成本 定义为：

cost(l, r) = nums[l] - nums[l + 1] + ... + nums[r] * (−1)r − l

你的任务是将 nums 分割成若干子数组，使得所有子数组的成本之和 最大化，并确保每个元素 正好 属于一个子数组。

具体来说，如果 nums 被分割成 k 个子数组，且分割点为索引 i1, i2, ..., ik − 1（其中 0 <= i1 < i2 < ... < ik - 1 < n - 1），则总成本为：

cost(0, i1) + cost(i1 + 1, i2) + ... + cost(ik − 1 + 1, n − 1)

返回在最优分割方式下的子数组成本之和的最大值。

注意：如果 nums 没有被分割，即 k = 1，则总成本即为 cost(0, n - 1)。
提示：

1 <= nums.length <= 105
-109 <= nums[i] <= 109
 */

public class L3196 {
    /*
        todo h
        1.首先根据数据规模判定只能用O(n)
        2.最直观的想法是以i开头，总成本最小。能想到的是挑一个j，使cost(i,j)+dp[j+1]最大化，但这样时间复杂度过不去
        3.因为每个nums[i]最终要么+，要么-，*** 对i的分析可以借助于对i+2的分析，与打家劫舍类似 ***
     */
    public long maximumTotalCost(int[] nums) {
        int n = nums.length;
        long[] dp = new long[n+1];
        for (int i = n - 1; i > -1; i--) {
            if(i == n - 1) {
                dp[i] = nums[i];
            } else {
                dp[i] = Math.max((long)nums[i] + dp[i+1], (long)nums[i] - nums[i+1] + dp[i+2]);
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(new L3196().maximumTotalCost(new int[]{1000000000,-1000000000,1000000000,-1000000000,1000000000}));
    }
}
