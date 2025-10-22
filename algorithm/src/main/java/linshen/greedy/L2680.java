package linshen.greedy;

/*
给你一个下标从 0 开始长度为 n 的整数数组 nums 和一个整数 k 。每一次操作中，你可以选择一个数并将它乘 2 。

你最多可以进行 k 次操作，请你返回 nums[0] | nums[1] | ... | nums[n - 1] 的最大值。

a | b 表示两个整数 a 和 b 的 按位或 运算。
            12 9
            1100
            1001

提示：

1 <= nums.length <= 105
1 <= nums[i] <= 109
1 <= k <= 15
 */

import java.util.HashSet;
import java.util.Set;

public class L2680 {
    //todo hhhhh 这么简单怎么没有想到呢？涉及到一连串的运算的，将前一部分和后一部分提前算出来保存！！！
    public long maximumOr(int[] nums, int k) {
        int n = nums.length;
        long[] prefix = new long[n + 1];
        long[] suffix = new long[n + 1];

        for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] | nums[i];
        for (int i = n - 1; i >= 0; i--) suffix[i] = suffix[i + 1] | nums[i];

        long ans = 0;
        for (int i = 0; i < n; i++) {
            long shifted = (long) nums[i] << k;
            ans = Math.max(ans, prefix[i] | shifted | suffix[i + 1]);
        }
        return ans;
    }

    public long maximumOr2(int[] nums, int k) {
        int n = nums.length;
        String[] ns = new String[n];
        int m = 0;
        for (int i = 0; i < n; i++) {
            ns[i] = Integer.toBinaryString(nums[i]);
            m = Math.max(m, ns[i].length());
        }
        for (int i = 0; i < ns.length; i++) {
            if(ns[i].length() < m) {
                int t = m - ns[i].length();
                for (int j = 0; j < t; j++){
                    ns[i] = "0" + ns[i];
                }
            }
        }
        Set<Integer>[] pos = new Set[m];
        for (int i = 0; i < m; i++) {
            pos[i] = new HashSet<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++){
                if(ns[i].charAt(j) == '1')pos[j].add(i);
            }
        }
        /*
        100000
        000001
        000002
         */
        long max = Long.MIN_VALUE;
        for (int i = 0; i < n; i++){
            if(ns[i].startsWith("1")) {
                for (int j = 0; j < m; j++){
                    if(ns[i].charAt(j) == '1')pos[j].remove(i);
                }
                long t = 0;
                for (int j = 0; j < m + k; j++){
                    long s = 0;
                    if(j < k)s = j < m ? ns[i].charAt(j) - '0' : 0;
                    else {
                        if(!pos[j - k].isEmpty() || (j < m && ns[i].charAt(j) == '1'))s = 1;
                    }
                    t = t * 2 + s;
                }
                for (int j = 0; j < m; j++){
                    if(ns[i].charAt(j) == '1')pos[j].add(i);
                }
                max = Math.max(max, t);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new L2680().maximumOr(new int[]{41,79,82,27,71,62,57,67,34,8,71,2,12,93,52,91,86,81,1,79,64,43,32,94,42,91,9,25}, 10));
    }
}
