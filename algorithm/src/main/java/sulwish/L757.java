package sulwish;

/*
给你一个二维整数数组 intervals ，其中 intervals[i] = [starti, endi] 表示从 starti
到 endi 的所有整数，包括 starti 和 endi 。

包含集合 是一个名为 nums 的数组，并满足 intervals 中的每个区间都 至少 有 两个 整数在 nums 中。

例如，如果 intervals = [[1,3], [3,7], [8,9]] ，那么 [1,2,4,7,8,9] 和 [2,3,4,8,9] 都符合
包含集合 的定义。
返回包含集合可能的最小大小。
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class L757 {
    public int intersectionSizeTwo(int[][] intervals) {
        List<List<Integer>> ins = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        return -1;
    }
}
