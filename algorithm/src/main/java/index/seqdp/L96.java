package index.seqdp;

/*
给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 */

public class L96 {
    public static void main(String[] args) {
        System.out.println(new L96().numTrees(3));
    }

    public int numTrees(int n) {
        int[][] f = new int[n][n];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    f[i][j] = 1;
                } else {

                    for (int k = i; k <= j; k++) {
                        if (k == i) {
                            f[i][j] += f[k + 1][j];
                        } else if (k == j) {
                            f[i][j] += f[i][k - 1];
                        } else {
                            f[i][j] += f[i][k - 1] * f[k + 1][j];
                        }
                    }
                }
            }
        }
//        for (int i = 0; i < n; i++){
//            System.out.println(Arrays.toString(f[i]));
//        }
        return f[0][n - 1];
    }
}
