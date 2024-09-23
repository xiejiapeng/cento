package index.nszh;

/*
给你一个长度为 n 的整数数组 nums 和 一个目标值 target。
请你从 nums 中选出三个整数，使它们的和与 target 最接近。

返回这三个数的和。

假定每组输入只存在恰好一个解。
 */


import java.util.Arrays;

public class L16 {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = -1;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++){
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int s = nums[i] + nums[left] + nums[right];
                if(s == target) {
                    return s;
                } else if(s < target) {
                    if(target - s < min) {
                        min = target - s;
                        ans = s;
                    }
                    left++;
                } else {
                    if(s - target < min) {
                        min = s - target;
                        ans = s;
                    }
                    right--;
                }
            }
        }
        return ans;
    }

}
