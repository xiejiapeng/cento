package linshen.structure;

/*
给你一个下标从 0 开始的整数数组 nums ，以及整数 modulo 和整数 k 。

请你找出并统计数组中 趣味子数组 的数目。

如果 子数组 nums[l..r] 满足下述条件，则称其为 趣味子数组 ：

在范围 [l, r] 内，设 cnt 为满足 nums[i] % modulo == k 的索引 i 的数量。并且 cnt % modulo == k 。
以整数形式表示并返回趣味子数组的数目。

注意：子数组是数组中的一个连续非空的元素序列。
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L2845 {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        long[] sum = new long[n+1];
        for (int i = 1; i <= n; i++){
            sum[i] = sum[i-1] + (nums.get(i-1) % modulo == k ? 1 : 0);
        }
        Map<Long, Long> see = new HashMap<>();
        see.put(0L, 1L);
        long ans = 0;
        for (int i = 0; i < n; i++){
            long p = sum[i+1] % modulo;
            long t = (modulo + (p-k)) % modulo;
            ans += see.getOrDefault(t, 0L);
            see.put(p, see.getOrDefault(p, 0L) + 1);
        }
        return ans;
    }
}
