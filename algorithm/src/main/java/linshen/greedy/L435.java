package linshen.greedy;
//给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。
// 返回 需要移除区间的最小数量，使剩余区间互不重
//叠 。
//
// 注意 只在一点上接触的区间是 不重叠的。例如 [1, 2] 和 [2, 3] 是不重叠的。
//

import java.util.Arrays;
import java.util.Comparator;

public class L435 {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        int last = Integer.MIN_VALUE;
        int cnt = 0;
        for (int[] is : intervals) {
            if(is[0] >= last) {
                last = is[1];
            } else {
                cnt++;
            }
        }
        return cnt;
    }
}
