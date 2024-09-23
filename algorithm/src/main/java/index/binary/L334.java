package index.binary;

/*
给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。

如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。


 */

public class L334 {
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        int[] rightLarge = new int[n];
        int[] leftSmall = new int[n];
        for (int i = n - 1; i > -1; i--) {
            if(i == n - 1)rightLarge[i] = Integer.MIN_VALUE;
            else rightLarge[i] = Math.max(rightLarge[i+1], nums[i+1]);
        }
        for (int i = 0; i < n; i++){
            if(i == 0)leftSmall[i] = Integer.MAX_VALUE;
            else leftSmall[i] = Math.min(leftSmall[i-1], nums[i-1]);
        }
        for (int i = 0; i < n; i++){
            if(leftSmall[i] < nums[i] && nums[i] < rightLarge[i])return true;
        }
        return false;
    }
}
