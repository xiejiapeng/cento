package index.nszh;

import java.util.*;
/*
给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。
请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：

0 <= a, b, c, d < n
a、b、c 和 d 互不相同
nums[a] + nums[b] + nums[c] + nums[d] == target
你可以按 任意顺序 返回答案 。
 */

public class L18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        long t = (long)target;
        Set<List<Integer>> ans = new HashSet<>();
        for (int i = 0; i < nums.length; i++){
            for(int j = i+1; j < nums.length; j++){
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    if((long)nums[i] + (long)nums[j] + (long)nums[left] + (long)nums[right] == t) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                    } else if((long)nums[i] + (long)nums[j] + (long)nums[left] + (long)nums[right] < t) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return new ArrayList<>(ans);
    }
}
