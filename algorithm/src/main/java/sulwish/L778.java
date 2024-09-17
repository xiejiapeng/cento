package sulwish;

/*
在一个 n x n 的整数矩阵 grid 中，每一个方格的值 grid[i][j] 表示位置 (i, j) 的平台高度。

当开始下雨时，在时间为 t 时，水池中的水位为 t 。你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位必须同时淹没这两个平台。假定你可以瞬间移动无限距离，
也就是默认在方格内部游动是不耗时的。当然，在你游泳的时候你必须待在坐标方格里面。

你从坐标方格的左上平台 (0，0) 出发。返回 你到达坐标方格的右下平台 (n-1, n-1) 所需的最少时间 。
 */

import java.util.LinkedList;
import java.util.Queue;

public class L778 {
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        int[][] grid = new int[][]{{0, 1, 2, 3, 4}, {24, 23, 22, 21, 5}, {12, 13, 14, 15, 16}, {11, 17, 18, 19, 20}, {10, 9, 8, 7, 6}};
        System.out.println(new L778().swimInWater(grid));
    }

    public int swimInWater(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        Queue<String> queue = new LinkedList<>();
        dist[m - 1][n - 1] = grid[m - 1][n - 1];
        queue.add(key(m - 1, n - 1));
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            if (cur.equals("4_3")) {
                System.out.println("h");
            }
            int c0 = Integer.valueOf(cur.split("_")[0]);
            int c1 = Integer.valueOf(cur.split("_")[1]);
            for (int[] dir : dirs) {
                int x = c0 + dir[0];
                int y = c1 + dir[1];
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    int newDist = Math.min(dist[x][y], Math.max(dist[c0][c1], grid[x][y]));
                    if (newDist != dist[x][y]) {
                        dist[x][y] = newDist;
//                        System.out.println("add " + key(x,y)+", dist " + newDist + " last is " + cur);
                        queue.add(key(x, y));
                    }
                }

            }
        }
        return dist[0][0];
    }

    private String key(int x, int y) {
        return x + "_" + y;
    }
}
