package sulwish;

/*
给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。

如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，
使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
 */

public class L334v2 {
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        int[] min = new int[n];
        int[] max = new int[n];
        for (int i = 0; i < n; i++){
            if(i == 0)min[i] = Integer.MAX_VALUE;
            else {
                min[i] = Math.min(min[i-1], nums[i-1]);
            }
        }
        for(int i = n - 1; i > -1; i--){
            if(i == n - 1)max[i] = Integer.MIN_VALUE;
            else {
                max[i] = Math.max(nums[i+1], max[i+1]);
            }
        }

        for (int i = 1 ; i < n - 1; i++){
            if(min[i] < nums[i] && nums[i] < max[i])return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new L334v2().increasingTriplet(new int[]{}));
    }
}
