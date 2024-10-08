package index.dualpointer;

/*
给你一个区间数组 intervals ，其中 intervals[i] = [starti, endi] ，且每个 starti 都 不同 。

区间 i 的 右侧区间 可以记作区间 j ，并满足 startj >= endi ，且 startj 最小化 。注意 i 可能等于 j 。

返回一个由每个区间 i 的 右侧区间 在 intervals 中对应下标组成的数组。如果某个区间 i
不存在对应的 右侧区间 ，则下标 i 处的值设为 -1 。

1 <= intervals.length <= 2 * 104
intervals[i].length == 2
-106 <= starti <= endi <= 106
每个间隔的起点都 不相同
 */

import java.util.Arrays;
import java.util.Comparator;

public class L436 {
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[][] f = new int[n][3];
        for (int i = 0; i < n; i++){
            f[i] = new int[]{intervals[i][0], intervals[i][1], i};
        }
        Arrays.sort(f, Comparator.comparingInt(o -> o[0]));
        int[] ans = new int[n];
        for (int i = 0; i < n; i++){
            int t = find(f, 0, n-1, intervals[i][1]);
            ans[i] = t == -1 ? -1 : f[t][2];
        }
        return ans;
    }

    //first larger than x
    private int find(int[][] f, int left, int right ,int x) {
        if(left == right) {
            if(f[left][0] >= x)return left;
            else return -1;
        } else if(left == right - 1) {
            if(f[left][0] >= x)return left;
            else if(f[right][0] >= x)return right;
            else return -1;
        } else {
            int mid = (left + right) / 2;
            if(f[mid][0] >= x)return find(f, left, mid, x);
            else return find(f, mid + 1, right, x);
        }
    }
}
