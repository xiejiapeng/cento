package linshen.greedy;

//给你一个二维整数数组 intervals ，其中 intervals[i] = [lefti, righti] 表示 闭 区间 [lefti,
//righti] 。
//
// 你需要将 intervals 划分为一个或者多个区间 组 ，每个区间 只 属于一个组，且同一个组中任意两个区间 不相交 。
//
// 请你返回 最少 需要划分成多少个组。
//
// 如果两个区间覆盖的范围有重叠（即至少有一个公共数字），那么我们称这两个区间是 相交 的。比方说区间 [1, 5] 和 [5, 8] 相交。
//

import java.util.*;

public class L2406 {
    public int minGroups(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
        int cnt = 0;
        //todo hhh 用TreeSet会去重，尽量用TreeMap
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            // 查找小于start的最大结束点
            Map.Entry<Integer, Integer> entry = map.lowerEntry(start);
            if (entry == null) {
                // 没有可以合并的组，新建一个组
                cnt++;
                map.put(end, map.getOrDefault(end, 0) + 1);
            } else {
                // 合并到已有的组
                int key = entry.getKey();
                int count = entry.getValue();
                if (count == 1) {
                    map.remove(key);
                } else {
                    map.put(key, count - 1);
                }
                map.put(end, map.getOrDefault(end, 0) + 1);
            }
        }
        return cnt;
    }
    public static void main(String[] args) {
        System.out.println(new L2406().minGroups(new int[][]{{4,5},{6,7},{8,9},{2,3},{1,10}}));
    }
}
