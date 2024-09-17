package sulwish;

/*
给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。

如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
 */

public class L334 {
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            left[i] = min;
            min = Math.min(min, nums[i]);
        }

        for (int i = n - 1; i > -1; i--) {
            right[i] = max;
            max = Math.max(max, nums[i]);
        }

        for (int i = 0; i < n; i++) {
            if (left[i] < nums[i] && nums[i] < right[i]) return true;
        }
        return false;
    }
}
