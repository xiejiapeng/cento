package linshen.dp;

/*
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
问总共有多少条不同的路径？
 */

public class L62 {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = m - 1; i > -1; i--){
            for (int j = n - 1; j > -1; j--) {
                if(i == m - 1 || j == n - 1) {
                    dp[i][j] = 1;
                }  else {
                    dp[i][j] = dp[i+1][j] + dp[i][j+1];
                }
            }
        }
        return dp[0][0];
    }
}
