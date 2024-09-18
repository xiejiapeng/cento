package index.bfs;

/*
在一个 n x n 的整数矩阵 grid 中，每一个方格的值 grid[i][j] 表示位置 (i, j) 的平台高度。

当开始下雨时，在时间为 t 时，水池中的水位为 t 。你可以从一个平台游向四周相邻的任意一个平台，
但是前提是此时水位必须同时淹没这两个平台。假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。当然，在你游泳的时候你必须待在坐标方格里面。

你从坐标方格的左上平台 (0，0) 出发。返回 你到达坐标方格的右下平台 (n-1, n-1) 所需的最少时间 。
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class L778 {
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[0][0] = 0;

        Queue<String> updated = new LinkedList<>();
        updated.add(sign(0, 0, 0));
        while (!updated.isEmpty()) {
            int size = updated.size();
            while (size-- > 0) {
                String sign = updated.poll();
                int[] s = design(sign);
                int x = s[0];
                int y = s[1];
                int h = s[2];
                for (int[] dir : dirs) {
                    int xx = x + dir[0];
                    int yy = y + dir[1];
                    if (xx >= 0 && xx < n && yy >= 0 && yy < m) {
                        int pre = dist[xx][yy];
                        int newh = Math.max(h, grid[xx][yy]);
                        if (newh < pre) {
                            dist[xx][yy] = newh;
                            updated.add(sign(xx, yy, newh));
                        }
                    }
                }
            }

        }
        return dist[n - 1][m - 1];

    }

    private String sign(int x, int y, int t) {
        return x + "_" + y + "_" + t;
    }

    private int[] design(String x) {
        String[] d = x.split("_");
        int[] ans = new int[3];
        for (int i = 0; i < 3; i++) {
            ans[i] = Integer.parseInt(d[i]);
        }
        return ans;
    }
}
