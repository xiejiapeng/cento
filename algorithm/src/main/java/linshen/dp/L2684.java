package linshen.dp;

/*
给你一个下标从 0 开始、大小为 m x n 的矩阵 grid ，矩阵由若干 正 整数组成。

你可以从矩阵第一列中的 任一 单元格出发，按以下方式遍历 grid ：

从单元格 (row, col) 可以移动到 (row - 1, col + 1)、(row, col + 1) 和 (row + 1, col + 1) 三个单元格中任一满足值 严格 大于当前单元格的单元格。
返回你在矩阵中能够 移动 的 最大 次数。
 */

public class L2684 {
    public int maxMoves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        int ans = 0;
        for (int j = n - 1; j > -1; j--){
            for (int i = m - 1; i > -1; i--) {
                if(j == n - 1) {
                    dp[i][j] = 0;
                } else {
                    if(i+1<m && grid[i+1][j+1] > grid[i][j]) {
                        dp[i][j] = Math.max(dp[i][j], 1 + dp[i+1][j+1]);
                    }

                    if(i-1>-1 && grid[i-1][j+1] > grid[i][j]) {
                        dp[i][j] = Math.max(dp[i][j], 1 + dp[i-1][j+1]);
                    }

                    if(grid[i][j+1] > grid[i][j]) {
                        dp[i][j] = Math.max(dp[i][j], 1 + dp[i][j+1]);
                    }
                    if(j == 0)ans = Math.max(ans, dp[i][j]);
                }
            }


        }
        return ans;
    }
}
