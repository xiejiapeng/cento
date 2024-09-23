package index.nszh;

import java.util.*;

/*
给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]]
满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。
请你返回所有和为 0 且不重复的三元组。

注意：答案中不可以包含重复的三元组。
 */

public class L15 {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> ans = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if(nums[i] + nums[left] + nums[right] == 0) {
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //todo update left or right
                    left++;
                } else if(nums[i] + nums[left] + nums[right] < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return new ArrayList<>(ans);
    }
}
