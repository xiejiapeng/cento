package sulwish;

import java.util.*;

public class L18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        Set<List<Integer>> ans = new HashSet<>();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int left = j + 1;
                int right = len - 1;

                while (left < right) {
                    if ((long) nums[i] + (long) nums[j] + (long) nums[left] + (long) nums[right] == (long) target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                    } else if (nums[i] + nums[j] + nums[left] + nums[right] < target) {
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
