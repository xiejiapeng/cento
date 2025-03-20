package linshen.slide;

/*
我们称一个分割整数数组的方案是 好的 ，当它满足：

数组被分成三个 非空 连续子数组，从左至右分别命名为 left ， mid ， right 。
left 中元素和小于等于 mid 中元素和，mid 中元素和小于等于 right 中元素和。
给你一个 非负 整数数组 nums ，请你返回 好的 分割 nums 方案数目。由于答案可能会很大，请你将结果对 109 + 7 取余后返回。
 */

import java.util.Arrays;

public class L1712 {
    //todo h: 分两步实现，注意边界条件
    int mod = (int)(1e9+7);
    public int waysToSplit(int[] nums) {
        int n = nums.length;

        //[i,j] => sum[i+1] - sum[i], (i,j] => sum[j+1] - sum[i+1], [i,j) => sum[j] - sum[i]
        int[] sum = new int[n+1];
        for (int i = 1; i <= n; i++){
            sum[i] = nums[i-1] + sum[i-1];
        }

        int mid = 0;
        int r = sum[n];
        long ans = 0;
        int[] lm = new int[n];
        //[l1,l2], right
        for (int left = 0, right = 0; right < n; right++){
            mid += nums[right];
            r -= nums[right];
            while (mid > r) {
                mid -= nums[left];
                left++;
            }
            lm[right] = left;
        }

        int l = 0;
        int[] rm = new int[n];
        mid = 0;
        for (int left = 0, right = 0; right < n; right++){
            mid += nums[right];
            while (left < right && l + nums[left] <= mid - nums[left]) {
                l += nums[left];
                mid -= nums[left];
                left++;
            }
            rm[right] = left;
        }
        for (int i = 0; i < n; i++){
            if(lm[i] > i)continue;
            if(i == 0 || i == n - 1)continue;
            if(lm[i] == 0)lm[i] = 1;
            if(rm[i] >= lm[i]) {
                ans += (rm[i] - lm[i] + 1);
                ans %= mod;
            }

        }

        System.out.println(Arrays.toString(lm));
        System.out.println(Arrays.toString(rm));


        return (int)(ans);
    }

    public static void main(String[] args) {
        System.out.println(new L1712().waysToSplit(new int[]{0,0,0}));
    }
}
