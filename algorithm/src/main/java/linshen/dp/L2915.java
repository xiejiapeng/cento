package linshen.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
给你一个下标从 0 开始的整数数组 nums 和一个整数 target 。

返回和为 target 的 nums 子序列中，子序列 长度的最大值 。如果不存在和为 target 的子序列，返回 -1 。

子序列 指的是从原数组中删除一些或者不删除任何元素后，剩余元素保持原来的顺序构成的数组。

1 <= nums.length <= 1000
1 <= nums[i] <= 1000
1 <= target <= 1000
 */

public class L2915 {
    public int lengthOfLongestSubsequence(List<Integer> nums, int target) {
        int n = nums.size();
        int[][] dp = new int[n][target+1];
        int max = -1;
        Map<Integer,Integer> mm = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int t = 1; t <= target; t++) {
                if(nums.get(i) == t)dp[i][t] = 1;
                else dp[i][t] = -1;
                if(t >= nums.get(i)) {
                    if(mm.containsKey(t-nums.get(i))) {
                        dp[i][t] = Math.max(dp[i][t], 1 + mm.get(t-nums.get(i)));
                    }
                }

            }
            //todo h 记忆化时，当前轮的状态不要影响当前，而是遍历完后再更新
            for (int t = 1; t <= target; t++){
                if(dp[i][t] != -1) {
                    mm.put(t, Math.max(mm.getOrDefault(t, -1), dp[i][t]));
                }
            }
            max = Math.max(max, dp[i][target]);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new L2915().lengthOfLongestSubsequence(Arrays.asList(1,2,3,4,5), 9));
    }
}
