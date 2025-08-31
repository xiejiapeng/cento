package linshen.structure.unionfind;

/*
给你一个下标从 1 开始的二进制矩阵，其中 0 表示陆地，1 表示水域。同时给你 row 和 col 分别表示矩阵中行和列的数目。

一开始在第 0 天，整个 矩阵都是 陆地 。但每一天都会有一块新陆地被 水 淹没变成水域。给你一个下标从 1 开始的二维数组 cells ，
其中 cells[i] = [ri, ci] 表示在第 i 天，第 ri 行 ci 列（下标都是从 1 开始）的陆地会变成 水域 （也就是 0 变成 1 ）。

你想知道从矩阵最 上面 一行走到最 下面 一行，且只经过陆地格子的 最后一天 是哪一天。你可以从最上面一行的
任意 格子出发，到达最下面一行的 任意 格子。你只能沿着 四个 基本方向移动（也就是上下左右）。

请返回只经过陆地格子能从最 上面 一行走到最 下面 一行的 最后一天 。
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class L1970 {
    int[][] dirs = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};

    static class UnionFind {
        private int[] parent;
        private int[] size;
        //todo hhh 注意，这里不需要用hashset，因为连通，所以只需要min=0,max=row即可；否则时间复杂度会超
        private int[] minRow;
        private int[] maxRow;

        public UnionFind(int n, int col) {
            parent = new int[n];
            size = new int[n];
            minRow = new int[n];
            maxRow = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
                int r = i / col;
                minRow[i] = r;
                maxRow[i] = r;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void merge(int x, int y) {
            int px = find(x);
            int py = find(y);
            if (px == py) return;

            if (size[px] < size[py]) {
                parent[px] = py;
                size[py] += size[px];
                minRow[py] = Math.min(minRow[py], minRow[px]);
                maxRow[py] = Math.max(maxRow[py], maxRow[px]);
            } else {
                parent[py] = px;
                size[px] += size[py];
                minRow[px] = Math.min(minRow[px], minRow[py]);
                maxRow[px] = Math.max(maxRow[px], maxRow[py]);
            }
        }

        public boolean canCross(int x, int row) {
            int root = find(x);
            return minRow[root] == 0 && maxRow[root] == row - 1;
        }
    }

    public int latestDayToCross(int row, int col, int[][] cells) {
        int n = cells.length;
        int[][] grid = new int[row][col];
        for (int[] cell : cells) {
            grid[cell[0] - 1][cell[1] - 1] = 1; // 先全部淹没
        }

        UnionFind uf = new UnionFind(row * col, col);
        boolean[][] land = new boolean[row][col];

        // 从最后一天往前回填
        for (int day = n - 1; day >= 0; day--) {
            int r = cells[day][0] - 1;
            int c = cells[day][1] - 1;
            land[r][c] = true;
            int id = r * col + c;

            // 与四邻居合并
            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr >= 0 && nr < row && nc >= 0 && nc < col && land[nr][nc]) {
                    uf.merge(id, nr * col + nc);
                }
            }

            // 检查当前连通块能否从上到下
            if (uf.canCross(id, row)) {
                return day;
            }
        }
        return 0;
    }
    public static void main(String[] args) {
        System.out.println(new L1970().latestDayToCross(6,2,new int[][]{{4,2},{6,2},{2,1},{4,1},{6,1},{3,1},{2,2},{3,2},{1,1},{5,1},{5,2},{1,2}}));
    }
}
