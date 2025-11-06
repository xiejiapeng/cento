package linshen.structure;

/*
给你一个 m x n 的矩阵 mat 和一个整数 k ，请你返回一个矩阵 answer ，其中每个 answer[i][j] 是所有满足下述条件的元素 mat[r][c] 的和：

i - k <= r <= i + k,
j - k <= c <= j + k 且
(r, c) 在矩阵内。
(lx,ly),(lx,ry)
(rx,ly),(rx,ry)
1 2
3 4
 */

public class L1314 {
    public static void main(String[] args) {
        System.out.println(new L1314().matrixBlockSum(new int[][]{{1, 2}, {3, 4}}, 1));
    }

    public int[][] matrixBlockSum(int[][] mat, int k) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] res = new int[n][m];
        int[][] sum = new int[n][m];
        //todo h 记住sum的计算方式，不要想当然
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    sum[i][j] = mat[i][j];
                } else if (i == 0) {
                    sum[i][j] = sum[i][j - 1] + mat[i][j];
                } else if (j == 0) {
                    sum[i][j] = sum[i - 1][j] + mat[i][j];
                } else {
                    sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + mat[i][j];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int rx = Math.min(i + k, n - 1);
                int ry = Math.min(j + k, m - 1);
                int lx = Math.max(i - k, 0);
                int ly = Math.max(j - k, 0);
                if (lx == 0 && ly == 0) {
                    res[i][j] = sum[rx][ry];
                } else if (lx == 0) {
                    res[i][j] = sum[rx][ry] - sum[rx][ly - 1];
                } else if (ly == 0) {
                    res[i][j] = sum[rx][ry] - sum[lx - 1][ry];
                } else {
                    res[i][j] = sum[rx][ry] - sum[lx - 1][ry] - sum[rx][ly - 1] + sum[lx][ly];
                }
            }
        }
        return res;
    }
}
