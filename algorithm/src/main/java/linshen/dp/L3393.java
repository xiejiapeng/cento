package linshen.dp;

/*
给你一个大小为 m x n 的二维整数数组 grid 和一个整数 k 。

你的任务是统计满足以下 条件 且从左上格子 (0, 0) 出发到达右下格子 (m - 1, n - 1) 的路径数目：

每一步你可以向右或者向下走，也就是如果格子存在的话，可以从格子 (i, j) 走到格子 (i, j + 1) 或者格子 (i + 1, j) 。
路径上经过的所有数字 XOR 异或值必须 等于 k 。
请你返回满足上述条件的路径总数。

提示：

1 <= m == grid.length <= 300
1 <= n == grid[r].length <= 300
0 <= grid[r][c] < 16
0 <= k < 16

(0, 0) → (1, 0) → (2, 0) → (2, 1) → (2, 2)

2 -> 7 -> 12 -> 6 -> 4
11

00010
00111
01100
00110
00100

            01011
            01001
            01110
            00010
            00100
 */

public class L3393 {
    int mod = (int)(1e9 + 7);
    public int countPathsWithXorValue(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        long[][][] dp = new long[m][n][100];
        for (int i = m - 1; i > -1; i--){
            for (int j = n - 1; j > -1; j--){
                for (int t = 0; t <= 16; t++){
                    if(i == 0 && j == 0){
                        System.out.println("f");
                    }
                    if(i == m - 1 && j == n - 1) {
                        if(t == grid[i][j])dp[i][j][t] = 1;
                        else dp[i][j][t] = 0;
                    } else if(i == m - 1) {
                        dp[i][j][t] = dp[i][j+1][t ^ grid[i][j]];
                    } else if(j == n - 1) {
                        dp[i][j][t] = dp[i+1][j][t ^ grid[i][j]];
                    } else {
                        //todo h 记住异或的这个性质，相同元素的异或具有消除性
                        dp[i][j][t] += dp[i+1][j][t ^ grid[i][j]];
                        dp[i][j][t] += dp[i][j+1][t ^ grid[i][j]];
                        dp[i][j][t] %= mod;
                    }
                }
            }
        }
        return (int)(dp[0][0][k] % mod);
    }

    public static void main(String[] args) {
        System.out.println(new L3393().countPathsWithXorValue(new int[][]{{2,1,5},{7,10,0},{12,6,4}}, 11));
    }
}
