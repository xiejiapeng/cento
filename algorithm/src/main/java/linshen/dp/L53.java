package linshen.dp;

/*
给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

子数组 是数组中的一个连续部分。
提示：

1 <= nums.length <= 105
-104 <= nums[i] <= 104
 */

public class L53 {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] s = new int[n+1];
        for (int i = 1; i <= n; i++){
            s[i] = s[i-1] + nums[i-1];
        }
        int min = 0;
        int ans = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++){
            ans = Math.max(ans, s[i] - min);
            min = Math.min(min, s[i]);
        }
        return ans;
    }
}
