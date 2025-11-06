package linshen.dp;

import java.util.List;

/*
给你一个整数数组 nums 。nums 的每个元素是 1，2 或 3。在每次操作中，
你可以删除 nums 中的一个元素。返回使 nums 成为 非递减 顺序所需操作数的 最小值。

提示：
1 <= nums.length <= 100
1 <= nums[i] <= 3
 */

public class L2826 {
    public int minimumOperations(List<Integer> nums) {
        int n = nums.size();
        int[] dp = new int[n];
        int max = 1;
        for (int i = n - 1; i > -1; i--){
            dp[i] = 1;
            for (int j = i + 1; j < n; j++){
                if(nums.get(j) >= nums.get(i)) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return nums.size() - max;
    }
}
