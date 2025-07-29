package linshen.structure;

/*
以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
[[1,4],[5,6]]
提示：

1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
//todo hhhh 第一版用的差分，无法解决[1,4],[5,6]这个情形。遇到区间合并，还是优先考虑排序后遍历吧

public class L56 {
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        int start = -1;
        int end = -1;
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        for (int[] ints : intervals) {
            int l = ints[0];
            int r = ints[1];
            if (start == -1) {
                start = l;
                end = r;
            } else {
                if (l <= end) {
                    end = Math.max(end, r);
                } else {
                    res.add(new int[]{start, end});
                    start = l;
                    end = r;
                }
            }
        }
        if (start != -1) {
            res.add(new int[]{start, end});
        }
        return res.toArray(new int[0][]);
    }
}
