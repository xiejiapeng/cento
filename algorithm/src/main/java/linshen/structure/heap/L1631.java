package linshen.structure.heap;

/*
你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col]
表示格子 (row, col) 的高度。一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1)
（注意下标从 0 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。

一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。

请你返回从左上角走到右下角的最小 体力消耗值 。

提示：

rows == heights.length
columns == heights[i].length
1 <= rows, columns <= 100
1 <= heights[i][j] <= 106
 */

import java.util.Arrays;
import java.util.PriorityQueue;

public class L1631 {
    int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    public int minimumEffortPath(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] time = new int[n][m];
        for (int i = 0; i < n; i++){
            Arrays.fill(time[i], Integer.MAX_VALUE);
        }
        PriorityQueue<String> all = new PriorityQueue<>((o1, o2) -> {
            int[] x = decode(o1);
            int[] y = decode(o2);
            return Integer.compare(time[x[0]][x[1]], time[y[0]][y[1]]);
        });
        time[0][0] = 0;
        all.add(encode(new int[]{0,0}));
        while (!all.isEmpty()) {
            int[] t = decode(all.poll());
            for (int[] dir : dirs) {
                if(t[0] + dir[0] > -1 && t[0] + dir[0] < n && t[1] + dir[1] > -1 && t[1] + dir[1] < m) {
                    int[] s = new int[]{t[0]+dir[0], t[1]+dir[1]};
                    if(Math.max(time[t[0]][t[1]], Math.abs(grid[s[0]][s[1]]-grid[t[0]][t[1]])) < time[s[0]][s[1]]) {
                        time[s[0]][s[1]] = Math.max(time[t[0]][t[1]], Math.abs(grid[s[0]][s[1]]-grid[t[0]][t[1]]));
                        all.remove(encode(s));
                        all.add(encode(s));
                    }
                }
            }
        }
        return time[n-1][m-1];
    }

    private String encode(int[] x) {
        return x[0] + "," + x[1];
    }

    private int[] decode(String s){
        String[] x = s.split(",");
        int[] a = new int[2];
        a[0] = Integer.parseInt(x[0]);
        a[1] = Integer.parseInt(x[1]);
        return a;
    }

    public static void main(String[] args) {
        System.out.println(new L1631().minimumEffortPath(new int[][]{{1,10,9}}));
    }
}
