package linshen.structure;

/*
给你一个整数数组 nums 和一个整数 k 。
返回 nums 中一个 非空子数组 的 最大 和，要求该子数组的长度可以 被 k 整除。
提示：
1 <= k <= nums.length <= 2 * 105
-109 <= nums[i] <= 109
 */

import java.util.HashMap;
import java.util.Map;

public class L3381 {
    public long maxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long[] sum = new long[n+1];
        for (int i = 1; i <= n; i++){
            sum[i] = sum[i-1] + nums[i-1];
        }
        long max = Long.MIN_VALUE;
        Map<Integer, Long> see = new HashMap<>();
        see.put(0, 0L);
        for (int i = 0; i < n; i++){
            int rdx = (i + 1) % k;
            if(see.containsKey(rdx)) {
                max = Math.max(max, sum[i+1] - see.get(rdx));
            }
            see.put(rdx, Math.min(see.getOrDefault(rdx, Long.MAX_VALUE), sum[i+1]));
        }
        return max;
    }
}
