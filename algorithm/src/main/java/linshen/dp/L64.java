package linshen.dp;

/*
给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
说明：每次只能向下或者向右移动一步。
 */

public class L64 {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m+1][n+1];
        for (int i = m - 1; i > -1; i--) {
            for (int j = n - 1; j > -1; j--) {
                if(i == m - 1 && j == n - 1) {
                    dp[i][j] = grid[i][j];
                } else if(i == m - 1) {
                    dp[i][j] = grid[i][j] + dp[i][j+1];
                } else if(j == n - 1) {
                    dp[i][j] = grid[i][j] + dp[i+1][j];
                } else {
                    dp[i][j] = grid[i][j] + Math.min(dp[i+1][j], dp[i][j+1]);
                }

            }
        }
        return dp[0][0];
    }
}
