package linshen.structure;

/*
给你一个下标从 0 开始的整数数组 nums 。现有一个长度等于 nums.length 的数组 arr 。对于满足 nums[j] == nums[i] 且 j != i 的
所有 j ，arr[i] 等于所有 |i - j| 之和。如果不存在这样的 j ，则令 arr[i] 等于 0 。

返回数组 arr 。
 */

import java.util.*;

public class L2615 {
    public static void main(String[] args) {
        System.out.println(new L2615().distance(new int[]{1, 3, 1, 1, 2}));
    }

    public long[] distance(int[] nums) {
        int n = nums.length;
        Map<Long, List<Long>> all = new HashMap<>();
        for (int i = 0; i < n; i++) {
            all.putIfAbsent((long) nums[i], new ArrayList<>());
            all.get((long) nums[i]).add((long) i);
        }

        long[] res = new long[n];
        for (List<Long> a : all.values()) {
            Collections.sort(a);
            System.out.println(a);
            int m = a.size();
            long[] sum = new long[m + 1];
            for (int i = 1; i <= m; i++) {
                sum[i] = sum[i - 1] + a.get(i - 1);
            }
            for (int i = 0; i < m; i++) {
                long t = a.get(i);
                res[(int) t] = i * t - sum[i] + (sum[m] - sum[i + 1]) - (m - i - 1) * t;
            }
        }
        return res;
    }
}
