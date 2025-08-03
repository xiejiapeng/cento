package linshen.structure;

/*
给你一个二维整数数组 intervals ，其中 intervals[i] = [lefti, righti] 表示 闭 区间 [lefti, righti] 。

你需要将 intervals 划分为一个或者多个区间 组 ，每个区间 只 属于一个组，且同一个组中任意两个区间 不相交 。

请你返回 最少 需要划分成多少个组。

如果两个区间覆盖的范围有重叠（即至少有一个公共数字），那么我们称这两个区间是 相交 的。比方说区间 [1, 5] 和 [5, 8] 相交。

todo hh 这是经典贪心题，但存在用差分的解法。思考一下为什么可以用差分
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class L2406 {
    public static void main(String[] args) {
        System.out.println(new L2406().minGroups(new int[][]{{5, 10}, {6, 8}, {1, 5}, {2, 3}, {1, 10}}));
    }

    public int minGroups(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int n = intervals.length;
        TreeMap<Integer, Integer> groups = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            int[] ins = intervals[i];
            if (groups.isEmpty()) {
                groups.put(ins[1], 1);
            } else {
                int x = groups.firstKey();
                if (x < ins[0]) {
                    int y = groups.get(x);
                    if (y > 1) {
                        groups.put(x, y - 1);
                    } else {
                        groups.remove(x);
                    }
                    groups.put(ins[1], groups.getOrDefault(ins[1], 0) + 1);
                } else {
                    groups.put(ins[1], groups.getOrDefault(ins[1], 0) + 1);
                }
            }
        }
        return groups.values().stream().mapToInt(i -> i).sum();
    }
}
