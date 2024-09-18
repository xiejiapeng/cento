package sulwish;

/*
给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

子数组
是数组中的一个连续部分。
 */

public class L53v2 {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int n = nums.length;
        int[] sum = new int[n+1];
        int min = 0;
        for(int i = 1; i <= n; i++){
            sum[i] = sum[i-1] + nums[i-1];
            max = Math.max(max, sum[i] - min);
            min = Math.min(min, sum[i]);
        }
        return max;
    }
}
