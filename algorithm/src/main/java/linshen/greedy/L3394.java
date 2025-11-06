package linshen.greedy;

/*
给你一个整数 n 表示一个 n x n 的网格图，坐标原点是这个网格图的左下角。同时给你一个二维坐标数组 rectangles ，
其中 rectangles[i] 的格式为 [startx, starty, endx, endy] ，表示网格图中的一个矩形。每个矩形定义如下：

(startx, starty)：矩形的左下角。
(endx, endy)：矩形的右上角。
注意 ，矩形相互之间不会重叠。你的任务是判断是否能找到两条 要么都垂直要么都水平 的 两条切割线 ，满足：

切割得到的三个部分分别都 至少 包含一个矩形。
每个矩形都 恰好仅 属于一个切割得到的部分。
如果可以得到这样的切割，请你返回 true ，否则返回 false 。
 */

import java.util.Arrays;
import java.util.Comparator;

public class L3394 {
    public static void main(String[] args) {
        System.out.println(new L3394().checkValidCuts(5, new int[][]{{1, 0, 5, 2}, {0, 2, 2, 4}, {3, 2, 5, 3}, {0, 4, 4, 5}}));
    }

    public boolean checkValidCuts(int n, int[][] rectangles) {
        int m = rectangles.length;
        int[][] i1 = new int[m][2];
        int[][] i2 = new int[m][2];
        for (int i = 0; i < m; i++) {
            i1[i] = new int[]{rectangles[i][0], rectangles[i][2]};
            i2[i] = new int[]{rectangles[i][1], rectangles[i][3]};
        }
        return check(m, i1) || check(m, i2);
    }

    private boolean check(int n, int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int last = intervals[0][1];
        int cnt = 0;
        for (int i = 1; i < n; i++) {
            if (intervals[i][0] >= last) {
                last = intervals[i][1];
                cnt++;
            } else {
                last = Math.max(last, intervals[i][1]);
            }
        }
        return cnt >= 2;
    }
}
