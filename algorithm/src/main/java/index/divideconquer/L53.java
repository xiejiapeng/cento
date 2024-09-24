package index.divideconquer;

/*
给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
子数组是数组中的一个连续部分。
 */

public class L53 {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] sum = new int[n+1];
        int min = 0;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++){
            sum[i+1] = sum[i] + nums[i];
            ans = Math.max(ans, sum[i+1] - min);
            min = Math.min(sum[i+1], min);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new L53().maxSubArray(new int[]{3,-5,1,-4,2}));
    }
}
