package linshen.structure;

/*
给你一个整数数组 nums 和一个整数 m。

返回任意大小为 m 的 子序列 中首尾元素乘积的最大值。

子序列 是可以通过删除原数组中的一些元素（或不删除任何元素），且不改变剩余元素顺序而得到的数组。
提示:
1 <= nums.length <= 105
-105 <= nums[i] <= 105
1 <= m <= nums.length
 */

public class L3584 {
    public long maximumProduct(int[] nums, int m) {
        int n = nums.length;
        long[] rmin = new long[n+1];
        long[] rmax = new long[n+1];
        rmin[n] = Integer.MAX_VALUE;
        rmax[n] = Integer.MIN_VALUE;
        for (int i = n - 1; i > -1; i--){
            rmin[i] = Math.min(rmin[i+1], nums[i]);
            rmax[i] = Math.max(rmax[i+1], nums[i]);
        }
        long max = Long.MIN_VALUE;
        for (int i = 0; i + m - 1 < n; i++){
            int j = i + m - 1;
            if(nums[i] >= 0) {
                max = Math.max(max, (long)nums[i] * rmax[j]);
            } else {
                max = Math.max(max, (long)nums[i] * rmin[j]);
            }
        }
        return max;
    }
}
