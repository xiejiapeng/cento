package linshen.zhizhen;

/*
给你一个整数数组 nums 和一个整数 k 。请你从 nums 中满足下述条件的全部子数组中找出最大子数组和：

子数组的长度是 k，且
子数组中的所有元素 各不相同 。
返回满足题面要求的最大子数组和。如果不存在子数组满足这些条件，返回 0 。

子数组 是数组中一段连续非空的元素序列。


 */

import java.util.HashMap;
import java.util.Map;

public class L2461 {
    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long sum = 0;
        long max = 0;
        Map<Integer, Integer> count = new HashMap<>();
        for (int right = 0; right < n; right++) {
            int left = right - k + 1;
            sum += nums[right];
            count.put(nums[right], count.getOrDefault(nums[right], 0) + 1);
            if(left - 1 >= 0) {
                sum -= nums[left - 1];
                count.put(nums[left-1], count.get(nums[left-1]) - 1);
                //todo 记住删除key
                if(count.get(nums[left-1]) == 0)count.remove(nums[left-1]);
            }
            if(count.keySet().size() == k) {
                max = Math.max(max, sum);
            }
        }
        return max;
    }
}
