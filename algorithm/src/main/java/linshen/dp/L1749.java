package linshen.dp;

/*
给你一个整数数组 nums 。一个子数组 [numsl, numsl+1, ..., numsr-1, numsr] 的
和的绝对值 为 abs(numsl + numsl+1 + ... + numsr-1 + numsr) 。

请你找出 nums 中 和的绝对值 最大的任意子数组（可能为空），并返回该 最大值 。

abs(x) 定义如下：

如果 x 是负整数，那么 abs(x) = -x 。
如果 x 是非负整数，那么 abs(x) = x 。
 */

public class L1749 {
    public int maxAbsoluteSum(int[] nums) {
        int x = maxSubArray(nums);
        int y = minSubArray(nums);
        return Math.max(Math.abs(x), Math.abs(y));
    }

    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] s = new int[n+1];
        for (int i = 1; i <= n; i++){
            s[i] = s[i-1] + nums[i-1];
        }
        int min = 0;
        int ans = 0;

        for (int i = 1; i <= n; i++){
            ans = Math.max(ans, s[i] - min);
            min = Math.min(min, s[i]);
        }
        return ans;
    }

    public int minSubArray(int[] nums) {
        int n = nums.length;
        int[] s = new int[n+1];
        for (int i = 1; i <= n; i++){
            s[i] = s[i-1] + nums[i-1];
        }
        int max = 0;
        int ans = 0;

        for (int i = 1; i <= n; i++){
            ans = Math.min(ans, s[i] - max);
            max = Math.max(max, s[i]);
        }
        return ans;
    }
}
