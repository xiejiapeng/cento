package linshen.structure.heap;

/*
给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，
请计算图中形状最多能接多少体积的雨水。
 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class L407 {
    //todo hhhhh 记住这个bfs模板题，与路径相关；只要节点的ans改了，且影响到了周边的节点，就要将它周边的节点扔进队列，从而将影响扩散出去；
    int[][] dirs = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;
        int[][] ans = new int[m][n];
        boolean[][] added = new boolean[m][n];
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> ans[o[0]][o[1]]));
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                ans[i][j] = Integer.MAX_VALUE;
                if(i == 0 || j == 0 || i == m-1 || j == n - 1) {
                    queue.add(new int[]{i, j});
                    ans[i][j] = heightMap[i][j];
                    added[i][j] = true;
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] q = queue.poll();
            for (int[] dir : dirs) {
                int x = q[0] + dir[0];
                int y = q[1] + dir[1];
                if(x >= 0 && x < m && y >= 0 && y < n){
                    int nh = Math.max(ans[q[0]][q[1]], heightMap[x][y]);
                    if(nh < ans[x][y]){
                        ans[x][y] = nh;
                        if(!added[x][y])queue.add(new int[]{x, y, ans[x][y]});
                    }
                }

            }
            added[q[0]][q[1]] = false;
        }
        int total = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                total += Math.max(0, ans[i][j] - heightMap[i][j]);
            }
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(new L407().trapRainWater(new int[][]{{1,4,3,1,3,2},{3,2,1,3,2,4},{2,3,3,2,3,1}}));
    }
}
