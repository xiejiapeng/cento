package linshen.greedy;

/*
给你一个下标从 0 开始、由 正整数 组成的数组 nums。

将数组分割成一个或多个 连续 子数组，如果不存在包含了相同数字的两个子数组，则认为是一种 好分割方案 。

返回 nums 的 好分割方案 的 数目。

由于答案可能很大，请返回答案对 109 + 7 取余 的结果。
 */

import java.util.*;

public class L2963 {
    int mod = (int) (1e9 + 7);

    public static void main(String[] args) {
        System.out.println(new L2963().numberOfGoodPartitions(new int[]{1, 2, 3, 4}));
    }

    public int numberOfGoodPartitions(int[] nums) {
        List<int[]> intervals = new ArrayList<>();
        int n = nums.length;
        Map<Integer, Integer> last = new HashMap<>();
        for (int i = n - 1; i > -1; i--) {
            last.putIfAbsent(nums[i], i);
        }
        Set<Integer> see = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (!see.contains(nums[i])) {
                intervals.add(new int[]{i, last.get(nums[i])});
                see.add(nums[i]);
            }
        }
        LinkedList<int[]> all = new LinkedList<>();
        for (int[] ints : intervals) {
            if (all.isEmpty()) all.addLast(ints);
            else {
                int[] l = all.getLast();
                if (l[1] >= ints[0]) {
                    l[1] = Math.max(l[1], ints[1]);
                } else {
                    all.addLast(ints);
                }
            }
        }

        long s = 0;
        long t = 0;
        for (int i = 1; i <= all.size(); i++) {
            t = 1 + s;
            t %= mod;
            s += t;
            s %= mod;
        }
        return (int) t;
    }
}
