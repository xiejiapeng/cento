package linshen.slide;

/*
给你一个正整数数组 nums ，请你从中删除一个含有 若干不同元素 的子数组。删除子数组的 得分
就是子数组各元素之 和 。

返回 只删除一个 子数组可获得的 最大得分 。

如果数组 b 是数组 a 的一个连续子序列，即如果它等于 a[l],a[l+1],...,a[r] ，那么它就是 a 的一个子数组。
 */

import java.util.HashSet;
import java.util.Set;

public class L1695 {
    public int maximumUniqueSubarray(int[] nums) {
        int n = nums.length;
        int max = 0;
        int point = 0;
        Set<Integer> seen = new HashSet<>();
        for (int left = 0, right = 0; right < n; right++){
            point += nums[right];
            while (seen.contains(nums[right])) {
                seen.remove(nums[left]);
                point -= nums[left];
                left++;
            }
            seen.add(nums[right]);
            max = Math.max(max, point);
        }
        return max;
    }
}
