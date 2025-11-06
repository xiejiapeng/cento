package linshen.dp;

/*
给你一个大小为 m x n 的网格和一个球。球的起始坐标为 [startRow, startColumn] 。
你可以将球移到在四个方向上相邻的单元格内（可以穿过网格边界到达网格之外）。你 最多 可以移动 maxMove 次球。

给你五个整数 m、n、maxMove、startRow 以及 startColumn ，找出并返回可以将球移出边界的路径数量。
因为答案可能非常大，返回对 109 + 7 取余 后的结果。
提示：

1 <= m, n <= 50
0 <= maxMove <= 50
0 <= startRow < m
0 <= startColumn < n
 */

public class L576 {
    int mod = (int)(1e9+7);
    long[][][] dp = new long[51][51][51];
    int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        for (int i = 0; i <= 50; i++){
            for (int j = 0; j <= 50; j++){
                for (int k = 0; k <= 50; k++){
                    dp[i][j][k] = -1;
                }
            }
        }
        return (int)(find(m,n,maxMove,startRow,startColumn) % mod);
    }
    public long find(int m, int n, int maxMove, int startRow, int startColumn) {
        if(dp[startRow][startColumn][maxMove] != -1) return dp[startRow][startColumn][maxMove];
        else {
            dp[startRow][startColumn][maxMove] = 0;
            if(maxMove == 0) {
                dp[startRow][startColumn][maxMove] = 0;
            } else if(maxMove == 1) {
                if(startRow == 0) {
                    dp[startRow][startColumn][maxMove]++;
                }
                dp[startRow][startColumn][maxMove] %= mod;
                if(startColumn == 0) {
                    dp[startRow][startColumn][maxMove]++;
                }
                dp[startRow][startColumn][maxMove] %= mod;
                if(startRow == m-1) {
                    dp[startRow][startColumn][maxMove]++;
                }
                dp[startRow][startColumn][maxMove] %= mod;
                if(startColumn == n-1) {
                    dp[startRow][startColumn][maxMove]++;
                }
                dp[startRow][startColumn][maxMove] %= mod;
            } else {
                for (int[] dir : dirs) {
                    int t = startRow + dir[0];
                    int s = startColumn + dir[1];
                    if(t == -1 || t == m || s == -1 || s == n) {
                        dp[startRow][startColumn][maxMove] += 1;
                        dp[startRow][startColumn][maxMove] %= mod;
                    } else {
                        dp[startRow][startColumn][maxMove] += find(m, n, maxMove - 1, t, s);
                        dp[startRow][startColumn][maxMove] %= mod;
                    }
                }
            }
        }
        return dp[startRow][startColumn][maxMove] % mod;
    }

    public static void main(String[] args) {
        System.out.println(new L576().findPaths(10,10,0,5,5));
    }
}
