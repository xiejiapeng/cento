package linshen.dp;

/*
给你一个整数数组 nums 。如果 nums 的子序列满足下述条件，则认为该子序列是一个 方波 ：

子序列的长度至少为 2 ，并且
将子序列从小到大排序 之后 ，除第一个元素外，每个元素都是前一个元素的 平方 。
返回 nums 中 最长方波 的长度，如果不存在 方波 则返回 -1 。

子序列 也是一个数组，可以由另一个数组删除一些或不删除元素且不改变剩余元素的顺序得到。

提示：

2 <= nums.length <= 105
2 <= nums[i] <= 105
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class L2501 {
    public int longestSquareStreak(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int[] dp = new int[n];
        Map<Integer, Integer> see = new HashMap<>();
        int max = -1;
        for (int i = n - 1; i > -1; i--) {
            dp[i] = -1;
            int target = nums[i] * nums[i];
            if(see.containsKey(target)) {
                int t = see.get(target);
                if(dp[t] == -1)dp[i] = Math.max(dp[i], 2);
                else {
                    dp[i] = Math.max(dp[i], 1 + dp[t]);
                }
            }
            max = Math.max(max, dp[i]);
            see.put(nums[i], i);
        }
        return max;
    }
}
