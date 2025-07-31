package linshen.structure;

/*
给你一个 无重叠的 ，按照区间起始端点排序的区间列表 intervals，其中 intervals[i] = [starti, endi] 表示第 i 个区间的开始和结束，
并且 intervals 按照 starti 升序排列。同样给定一个区间 newInterval = [start, end] 表示另一个区间的开始和结束。

在 intervals 中插入区间 newInterval，使得 intervals 依然按照 starti 升序排列，且区间之间不重叠（如果有必要的话，可以合并区间）。

返回插入之后的 intervals。

注意 你不需要原地修改 intervals。你可以创建一个新数组然后返回它。
 */

import java.util.ArrayList;
import java.util.List;

//todo hhh
public class L57 {
    public static void main(String[] args) {
        System.out.println(new L57().insert(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}}, new int[]{4, 8}));
    }

    public int[][] insert(int[][] intervals, int[] f) {
        int n = intervals.length;
        List<int[]> res = new ArrayList<>();
        boolean seen = false;
        int next = 0;
        int start = -1;
        int end = -1;
        /*
        is[0], is[1], f[0], f[1]
         */
        while (next < n) {
            int[] is = intervals[next];
            if (start == -1) {
                if (seen) {
                    res.add(is);
                } else {
                    if (is[1] < f[0]) {
                        res.add(is);
                    } else if (is[0] > f[1]) {
                        res.add(f);
                        seen = true;
                        res.add(is);
                    } else {
                        start = Math.min(is[0], f[0]);
                        end = Math.max(is[1], f[1]);
                        seen = true;
                    }
                }

            } else {
                if (is[0] > end) {
                    res.add(new int[]{start, end});
                    start = -1;
                    end = -1;
                    res.add(is);
                } else {
                    start = Math.min(is[0], start);
                    end = Math.max(is[1], end);
                }
            }
            next++;
        }
        if (start != -1) {
            res.add(new int[]{start, end});
        }

        if (!seen) {
            res.add(new int[]{f[0], f[1]});
        }
        return res.toArray(new int[0][]);
    }

}
