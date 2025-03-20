package linshen.slide;

/*
给你一个整数数组 nums 和两个整数 indexDiff 和 valueDiff 。

找出满足下述条件的下标对 (i, j)：

i != j,
abs(i - j) <= indexDiff
abs(nums[i] - nums[j]) <= valueDiff
如果存在，返回 true ；否则，返回 false 。
 */

import java.util.TreeMap;

public class L220 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        int n = nums.length;
        indexDiff++;
        //todo 记住这个非常常用的数据结构
        TreeMap<Integer, Integer> cnt = new TreeMap<>();
        for (int right = 0; right < n; right++){
            int left = right - indexDiff + 1;
            if(left - 1 >= 0) {
                cnt.put(nums[left-1], cnt.get(nums[left-1]) - 1);
                if(cnt.get(nums[left-1]) == 0) {
                    cnt.remove(nums[left-1]);
                }
            }
            Integer floor = cnt.floorKey(nums[right]);
            if(floor != null && Math.abs(floor - nums[right]) <= valueDiff)return true;
            Integer ceiling = cnt.ceilingKey(nums[right]);
            if(ceiling != null && Math.abs(nums[right] - ceiling) <= valueDiff)return true;
            cnt.put(nums[right], cnt.getOrDefault(nums[right], 0) + 1);
        }
        return false;
    }
}
