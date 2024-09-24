package index.prefix;

/*
给你一个整数数组 nums 。一个子数组 [numsl, numsl+1, ..., numsr-1, numsr] 的 和的绝对值 为 abs(numsl + numsl+1 + ... + numsr-1 + numsr) 。

请你找出 nums 中 和的绝对值 最大的任意子数组（可能为空），并返回该 最大值 。

abs(x) 定义如下：

如果 x 是负整数，那么 abs(x) = -x 。
如果 x 是非负整数，那么 abs(x) = x 。
 */

public class L1749 {
    public int maxAbsoluteSum(int[] nums) {
        int ans = 0;
        int n = nums.length;
        int s = 0;
        int min = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++){
            s += nums[i];
            max = Math.max(max, s - min);
            min = Math.min(min, s);
        }
        ans = Math.max(ans, Math.abs(max));

        s = 0;
        min = Integer.MAX_VALUE;
        max = 0;
        for (int i = 0; i < n; i++){
            s += nums[i];
            min = Math.min(min, s - max);
            max = Math.max(max, s);
        }
        ans = Math.max(ans, Math.abs(min));
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new L1749().maxAbsoluteSum(new int[]{1,-3,2,3,-4}));
    }
}
