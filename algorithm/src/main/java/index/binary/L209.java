package index.binary;

/*
给定一个含有 n 个正整数的数组和一个正整数 target 。

找出该数组中满足其总和大于等于 target 的长度最小的
子数组
 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 */

public class L209 {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        int left = 0;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++){
            sum[i+1] = nums[i] + sum[i];
            while (sum[i+1] - sum[left] >= target) {
                ans = Math.min(ans, i+1-left);
                left++;
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
