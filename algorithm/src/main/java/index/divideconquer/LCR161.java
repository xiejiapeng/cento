package index.divideconquer;
/*
某公司每日销售额记于整数数组 sales，请返回所有 连续 一或多天销售额总和的最大值。

要求实现时间复杂度为 O(n) 的算法。
 */

public class LCR161 {
    public int maxSales(int[] nums) {
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
}
