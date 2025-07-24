package linshen.structure;

/*
给你一个正整数数组 nums，请你移除 最短 子数组（可以为 空），使得剩余元素的 和 能被 p 整除。 不允许 将整个数组都移除。

请你返回你需要移除的最短子数组的长度，如果无法满足题目要求，返回 -1 。

子数组 定义为原数组中连续的一组元素。
提示：
1 <= nums.length <= 105
1 <= nums[i] <= 109
1 <= p <= 109
 */

import java.util.HashMap;
import java.util.Map;

public class L1590 {
    public int minSubarray(int[] nums, int p) {
        int n = nums.length;
        long[] sum = new long[n+1];
        for (int i = 1; i <= n; i++){
            sum[i] = sum[i-1] + nums[i-1];
        }
        long r = sum[n] % p;
        if(r == 0)return 0;
        int min = Integer.MAX_VALUE;
        Map<Long, Integer> next = new HashMap<>();
        next.put(0L, 0);
        /*
        pa+r-pb-s+x=p(a-b)+(r-s+x)
         */
        for (int i = 0; i < n; i++){
            long s = sum[i+1] % p;
            long x = (p+(s-r))%p;
            if(next.containsKey(x)) {
                int start = next.get(x);
                min = Math.min(min, i - start + 1);
            }
            next.put(s, i+1);
        }

        if(min == n || min == Integer.MAX_VALUE)return -1;
        else return min;
    }
}
