package linshen.zhizhen;

/*
给你一个整数数组 nums 和一个整数 k ，请你返回 nums 中 好 子数组的数目。
一个子数组 arr 如果有 至少 k 对下标 (i, j) 满足 i < j 且 arr[i] == arr[j] ，那么称它是一个 好 子数组。
子数组 是原数组中一段连续 非空 的元素序列。
 */

import java.util.HashMap;
import java.util.Map;

public class L2537 {
    public long countGood(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> all = new HashMap<>();
        //用long
        long ans = 0;
        int cnt = 0;
        for (int left = 0, right = 0; right < n; right++){
            cnt += all.getOrDefault(nums[right], 0);
            all.put(nums[right], all.getOrDefault(nums[right], 0) + 1);
            //todo 注意这里的-1，left只对其他元素（不包含自身）造成影响
            while (cnt - (all.get(nums[left]) - 1) >= k) {
                cnt -= (all.get(nums[left]) - 1);
                //记得同时更新all
                all.put(nums[left], all.get(nums[left]) - 1);
                left++;
            }
            //注意这里的判断条件，cnt>=k!!!
            if(cnt >= k)ans += (left + 1);
        }
        return ans;
    }
}
