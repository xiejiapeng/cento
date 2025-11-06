package linshen.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
给你一个由 正整数 组成、大小为 m x n 的矩阵 grid。你可以从矩阵中的任一单元格移动到另一个
位于正下方或正右侧的任意单元格（不必相邻）。从值为 c1 的单元格移动到值为 c2 的单元格的得分为 c2 - c1 。

你可以从 任一 单元格开始，并且必须至少移动一次。

返回你能得到的 最大 总得分。
 */

public class L3148 {
    public int maxScore(List<List<Integer>> grid) {
        int m = grid.size();
        int n = grid.get(0).size();
        int[][] dp = new int[m][n];
        int[][] idle = new int[m][n];
        int[] row = new int[m];
        int[] col = new int[n];
        Arrays.fill(row, -1);
        Arrays.fill(col, -1);
        int max = -100;
        for (int i = m - 1; i > -1; i--){
            for (int j = n - 1; j > -1; j--) {
                if(i == m - 1 && j == n - 1) {
                    dp[i][j] = Integer.MIN_VALUE;
                    idle[i][j] = 0;
                    row[i] = grid.get(i).get(j);
                    col[j] = grid.get(i).get(j);
                    continue;
                }
                dp[i][j] = Integer.MIN_VALUE;
                idle[i][j] = 0;

                if(j != n-1){
                    dp[i][j] = Math.max(dp[i][j], row[i] - grid.get(i).get(j));
                    idle[i][j] = Math.max(idle[i][j], row[i] - grid.get(i).get(j));
                }

                if(i != m-1) {
                    dp[i][j] = Math.max(dp[i][j], col[j] - grid.get(i).get(j));
                    idle[i][j] = Math.max(idle[i][j], col[j] - grid.get(i).get(j));
                }


                row[i] = Math.max(row[i], grid.get(i).get(j) + idle[i][j]);
                col[j] = Math.max(col[j], grid.get(i).get(j) + idle[i][j]);
                if(dp[i][j] == 0) {
                    System.out.println("f " + i + ","+ j);
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        List<List<Integer>> grid = new ArrayList<>();
        grid.add(Arrays.asList(4,3,2));
        grid.add(Arrays.asList(3,2,1));
        System.out.println(new L3148().maxScore(grid));
    }
}
