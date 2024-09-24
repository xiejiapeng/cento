package index.prefix;

/*
给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。

子数组是数组中元素的连续非空序列。

1 <= nums.length <= 2 * 104
-1000 <= nums[i] <= 1000
-107 <= k <= 107
 */

import java.util.HashMap;
import java.util.Map;

public class L560 {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        Map<Integer,Integer> map = new HashMap<>();
        int s = 0;
        map.put(0, 1);
        int ans = 0;
        for (int i = 0; i < n; i++){
            s += nums[i];
            int target = s - k;
            ans += map.getOrDefault(target, 0);
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        return ans;
    }
}
