package sulwish;

/*
给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。

子数组是数组中元素的连续非空序列。
 */

import java.util.HashMap;
import java.util.Map;

public class L560 {
    public int subarraySum(int[] nums, int k) {
        Map<Integer,Integer> s = new HashMap<>();
        s.put(0, 1);
        int sum = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
            int target = sum - k;
            int cnt = s.getOrDefault(target, 0);
            ans += cnt;
            s.put(sum, s.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }
}
