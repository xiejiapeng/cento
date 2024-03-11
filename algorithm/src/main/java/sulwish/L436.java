package sulwish;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.TreeSet;

/*
给你一个区间数组 intervals ，其中 intervals[i] = [starti, endi] ，且每个 starti 都 不同 。

区间 i 的 右侧区间 可以记作区间 j ，并满足 startj >= endi ，且 startj 最小化 。注意 i 可能等于 j 。

返回一个由每个区间 i 的 右侧区间 在 intervals 中对应下标组成的数组。如果某个区间 i 不存在对应的 右侧区间 ，则下标 i 处的值设为 -1 。
 */
public class L436 {
    //[3,4], [2,3], [1,2], [-1,0,1]
    public int[] findRightInterval(int[][] intervals) {
        TreeSet<int[]> x = new TreeSet<>(Comparator.comparingInt(o -> o[0]));
        for (int i = 0; i < intervals.length; i++){
            x.add(new int[]{intervals[i][0], i});
        }
        int[] ans = new int[intervals.length];
        for (int i = 0; i < ans.length; i++){
            int[] a = x.ceiling(new int[]{intervals[i][1], i});
            if(a == null)ans[i] = -1;
            else ans[i] = a[1];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] x = new int[][]{{1,4}, {2,3}, {3,4}};
        System.out.println(Arrays.toString(new L436().findRightInterval(x)));
    }
}
