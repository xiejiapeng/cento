package sulwish;

import java.util.Arrays;

public class L16 {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        int ans = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] == target) {
                    return target;
                } else if (nums[i] + nums[left] + nums[right] > target) {
                    if (nums[i] + nums[left] + nums[right] - target < min) {
                        min = nums[i] + nums[left] + nums[right] - target;
                        ans = nums[i] + nums[left] + nums[right];
                    }
                    right--;
                } else {
                    if (target - (nums[i] + nums[left] + nums[right]) < min) {
                        min = target - (nums[i] + nums[left] + nums[right]);
                        ans = nums[i] + nums[left] + nums[right];
                    }
                    left++;
                }
            }
        }
        return ans;
    }
}
