package linshen.backtrace;

/*
给你一个大小为 3 * 3 ，下标从 0 开始的二维整数矩阵 grid ，分别表示每一个格子里石头的数目。
网格图中总共恰好有 9 个石头，一个格子里可能会有 多个 石头。

每一次操作中，你可以将一个石头从它当前所在格子移动到一个至少有一条公共边的相邻格子。

请你返回每个格子恰好有一个石头的 最少移动次数 。
 */

import java.util.HashSet;
import java.util.Set;

public class L2850 {
    int ans = Integer.MAX_VALUE;
    public int minimumMoves(int[][] grid) {
        Set<int[]> empty = new HashSet<>();
        Set<int[]> redunc = new HashSet<>();
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if(grid[i][j] == 0)empty.add(new int[]{i,j});
                if(grid[i][j] > 1)redunc.add(new int[]{i,j});
            }
        }
        dfs(grid, empty, redunc, 0);
        return ans;
    }

    private void dfs(int[][] cur, Set<int[]> empty, Set<int[]> redunc, int cnt) {
        if(empty.isEmpty()) {
            ans = Math.min(ans, cnt);
        } else {
            for (int[] e : empty) {
                for (int[] r : redunc) {
                    int dist = Math.abs(e[0] - r[0]) + Math.abs(e[1] - r[1]);

                    Set<int[]> newEmpty = new HashSet<>(empty);
                    Set<int[]> newRedunc = new HashSet<>(redunc);
                    cur[e[0]][e[1]] = 1;
                    cur[r[0]][r[1]] -= 1;
                    newEmpty.remove(e);
                    if(cur[r[0]][r[1]] == 1){
                        newRedunc.remove(r);
                    }


                    dfs(cur, newEmpty, newRedunc, cnt + dist);
                    //todo hhh 注意，cur没有用新的集合，所以要还原状态
                    cur[r[0]][r[1]] += 1;
                }
            }
        }
    }
}
