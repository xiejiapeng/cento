package linshen.structure;

/*
给你一个整数数组 nums。
特殊三元组 定义为满足以下条件的下标三元组 (i, j, k)：

0 <= i < j < k < n，其中 n = nums.length
nums[i] == nums[j] * 2
nums[k] == nums[j] * 2
返回数组中 特殊三元组 的总数。

由于答案可能非常大，请返回结果对 109 + 7 取余数后的值。
 */

import java.util.HashMap;
import java.util.Map;

public class L3583 {
    int mod = (int)(1e9+7);
    public int specialTriplets(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> all = new HashMap<>();
        long[] right = new long[n];
        for (int i = n - 1; i > -1; i--) {
            right[i] = all.getOrDefault(nums[i] * 2, 0);
            all.put(nums[i], all.getOrDefault(nums[i], 0) + 1);
        }
        all = new HashMap<>();
        long[] left = new long[n];
        for (int i = 0; i < n; i++){
            left[i] = all.getOrDefault(nums[i] * 2, 0);
            all.put(nums[i], all.getOrDefault(nums[i], 0) + 1);
        }

        long ans = 0;
        for (int i = 0; i < n; i++){
            ans += (left[i] * right[i]);
            ans %= mod;
        }
        return (int)(ans % mod);
    }
}
