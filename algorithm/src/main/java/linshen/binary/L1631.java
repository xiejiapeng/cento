package linshen.binary;

/*
你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col) 的高度。一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。

一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。

请你返回从左上角走到右下角的最小 体力消耗值 。

rows == heights.length
columns == heights[i].length
1 <= rows, columns <= 100
1 <= heights[i][j] <= 106
 */

import java.util.LinkedList;
import java.util.List;

public class L1631 {
    int[][] dirs = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    // todo h 可以作为典型题型重点掌握
    public int minimumEffortPath(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int[] height : heights) {
            for (int j = 0; j < m; j++) {
                min = Math.min(min, height[j]);
                max = Math.max(max, height[j]);
            }
        }
        //注意left=0
        return findLeast(heights, 0, max - min);
    }

    private int findLeast(int[][] heights, int left ,int right) {
        if(left == right)return left;
        else if(left == right - 1) {
            if(check(heights, left))return left;
            else return right;
        } else {
            int mid = (left + right) / 2;
            if(check(heights, mid)) return findLeast(heights, left, mid);
            else return findLeast(heights, mid + 1, right);
        }
    }

    private boolean check(int[][] heights, int target) {
        int n = heights.length;
        int m = heights[0].length;
        boolean[][] visited = new boolean[n][m];
        LinkedList<int[]> next = new LinkedList<>();
        next.addLast(new int[]{0,0});
        while (!next.isEmpty()) {
            int[] x = next.poll();
            for (int[] dir : dirs) {
                int a = x[0] + dir[0];
                int b = x[1] + dir[1];
                if(a >= 0 && a < n && b >= 0 && b < m && Math.abs(heights[x[0]][x[1]] - heights[a][b]) <= target) {
                    if(!visited[a][b]) {
                        visited[a][b] = true;
                        next.addLast(new int[]{a,b});
                    }
                }
            }
        }
        return visited[n-1][m-1];
    }

    public static void main(String[] args) {
        System.out.println(new L1631().minimumEffortPath(new int[][]{{1,2,2},{3,8,2},{5,3,5}}));
    }
}
