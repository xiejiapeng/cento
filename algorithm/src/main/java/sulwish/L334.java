package sulwish;

/*
给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。

如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，
使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false
 */

public class L334 {
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        int[] min = new int[n];
        int[] max = new int[n];
        min[0] = Integer.MAX_VALUE;
        max[n-1] = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++){
            min[i] = Math.min(nums[i-1], min[i-1]);
        }
        for (int i = n - 2; i > -1; i--){
            max[i] = Math.max(nums[i+1], max[i+1]);
        }
        for (int i = 1; i < n - 1; i++){
            int l = min[i];
            int r = max[i];
            if(l < nums[i] && nums[i] < r)return true;
        }
        return false;
    }
}
