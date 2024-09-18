package sulwish;

public class L576 {
    int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    int mod = (int)(1e9+7);
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        long[][][] f = new long[maxMove+1][m][n];

        //i,j use move steps
        for (int move = 1; move <= maxMove; move++){
            for (int i = 0; i < m; i++){
                for (int j = 0; j < n; j++){
                    if(move == 1) {
                        if(i == 0)f[move][i][j]++;
                        if(i == m - 1)f[move][i][j]++;
                        if(j == 0)f[move][i][j]++;
                        if(j == n - 1)f[move][i][j]++;
                    } else {
                        for (int[] dir : dirs) {
                            int x = i + dir[0];
                            int y = j + dir[1];
                            if(x >= 0 && x < m && y >= 0 && y < n) {
                                f[move][i][j] += f[move-1][x][y];
                                f[move][i][j] %= mod;
                            }
                        }
                    }
                }
            }
        }
        long total = 0;
        for (int i = 1; i <= maxMove; i++){
            total += f[i][startRow][startColumn];
            total %= mod;
        }
        return (int)(total % mod);
    }
}
