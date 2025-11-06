package linshen.dp;
/*
给你一个整数数组 nums。好子序列 的定义是：子序列中任意 两个 连续元素的绝对差 恰好 为 1。

子序列 是指可以通过删除某个数组的部分元素（或不删除）得到的数组，并且不改变剩余元素的顺序。

返回 nums 中所有 可能存在的 好子序列的 元素之和。

因为答案可能非常大，返回结果需要对 109 + 7 取余。

注意，长度为 1 的子序列默认为好子序列。

提示：

1 <= nums.length <= 105
0 <= nums[i] <= 105
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L3351 {
    int mod = (int)(1e9+7);
    public int sumOfGoodSubsequences(int[] nums) {
        int n = nums.length;
        /*
        [i,..]
        以x开头的子序列个数，元素之和
         */
        Map<Integer, long[]> dp = new HashMap<>();
        for (int i = n - 1; i > -1; i--){
            if(i == n - 1) {
                dp.put(nums[i], new long[]{1, nums[i]});
            } else {
                Map<Integer, long[]> right = dp;
                int t = nums[i] - 1;
                if(right.containsKey(t)) {
                    long x = right.get(t)[0];
                    long y = right.get(t)[1];
                    long[] u = dp.getOrDefault(nums[i], new long[]{0,0});
                    u[0] += x;
                    u[1] += (x % mod) * (long)nums[i] + y;
                    u[1] %= mod;
                    dp.put(nums[i], u);
                }
                t = nums[i] + 1;
                if(right.containsKey(t)) {
                    long x = right.get(t)[0];
                    long y = right.get(t)[1];
                    long[] u = dp.getOrDefault(nums[i], new long[]{0,0});
                    u[0] += x;
                    u[1] += (x % mod) * (long)nums[i] + y;
                    u[1] %= mod;
                    dp.put(nums[i], u);
                }
                long[] u = dp.getOrDefault(nums[i], new long[]{0,0});
                u[0] += 1;
                u[1] += nums[i];
                u[1] %= mod;
                dp.put(nums[i], u);
            }
        }
        long sum = 0;
        for (int x : dp.keySet()) {
            sum += dp.get(x)[1];
            sum %= mod;
        }
        return (int)(sum % mod);
    }

    public static void main(String[] args) {
        System.out.println(new L3351().sumOfGoodSubsequences(new int[]{1,2,1}));
    }
}
