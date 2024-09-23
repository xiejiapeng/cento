package index.binary;

/*
给你一个区间数组 intervals ，其中 intervals[i] = [starti, endi] ，且每个 starti 都 不同 。

区间 i 的 右侧区间 可以记作区间 j ，并满足 startj >= endi ，且 startj 最小化 。注意 i 可能等于 j 。

返回一个由每个区间 i 的 右侧区间 在 intervals 中对应下标组成的数组。
如果某个区间 i 不存在对应的 右侧区间 ，则下标 i 处的值设为 -1 。
 */

import org.apache.spark.sql.sources.In;

import java.util.Arrays;
import java.util.Comparator;

public class L436 {
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[] ans = new int[n];
        int[][] is = new int[n][3];
        for (int i = 0; i < n; i++){
            is[i] = new int[]{intervals[i][0], intervals[i][1], i};
        }
        Arrays.sort(is, Comparator.comparingInt(o -> o[0]));
        for (int i = 0; i < n; i++){
            ans[is[i][2]] = find(is, is[i][1], 0, n-1);
        }

        return ans;
    }

    //least x that is[x][0] >= end
    private int find(int[][] is, int end, int left, int right) {
        if(left == right) {
            if(is[left][0] >= end)return is[left][2];
            else return -1;
        } else if(left == right - 1) {
            if(is[left][0] >= end)return is[left][2];
            else if(is[right][0] >= end) return is[right][2];
            else return -1;
        } else {
            int mid = (left + right) / 2;
            if(is[mid][0] >= end){
                return find(is, end, left, mid);
            } else {
                return find(is, end, mid + 1, right);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new L436().findRightInterval(new int[][]{{1,4},{2,3},{3,4}})));
    }
}
