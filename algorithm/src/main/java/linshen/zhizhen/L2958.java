package linshen.zhizhen;

/*
给你一个整数数组 nums 和一个整数 k 。

一个元素 x 在数组中的 频率 指的是它在数组中的出现次数。

如果一个数组中所有元素的频率都 小于等于 k ，那么我们称这个数组是 好 数组。

请你返回 nums 中 最长好 子数组的长度。

子数组 指的是一个数组中一段连续非空的元素序列。


 */

import java.util.HashMap;
import java.util.Map;

public class L2958 {
    public int maxSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> cnt = new HashMap<>();
        int max = 0;
        for (int left = 0, right = 0; right < n; right++){
            cnt.put(nums[right], cnt.getOrDefault(nums[right], 0) + 1);
            while (cnt.get(nums[right]) > k) {
                cnt.put(nums[left], cnt.get(nums[left]) - 1);
                if(cnt.get(nums[left]) == 0)cnt.remove(nums[left]);
                left++;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
