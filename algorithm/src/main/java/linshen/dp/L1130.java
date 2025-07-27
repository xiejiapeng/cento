package linshen.dp;

/*
给你一个正整数数组 arr，考虑所有满足以下条件的二叉树：

每个节点都有 0 个或是 2 个子节点。
数组 arr 中的值与树的中序遍历中每个叶节点的值一一对应。
每个非叶节点的值等于其左子树和右子树中叶节点的最大值的乘积。
在所有这样的二叉树中，返回每个非叶节点的值的最小可能总和。这个和的值是一个 32 位整数。

如果一个节点有 0 个子节点，那么该节点为叶节点。
 */

public class L1130 {
    public static void main(String[] args) {
        System.out.println(new L1130().mctFromLeafValues(new int[]{6, 2, 4}));
    }

    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        int[][] max = new int[n][n];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    max[i][j] = arr[i];
                } else {
                    max[i][j] = Math.max(arr[i], max[i + 1][j]);
                }
            }
        }
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    dp[i][j] = 0;
                } else if (len == 2) {
                    dp[i][j] = arr[i] * arr[j];
                } else {
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int t = i; t < j; t++) {
                        if (i == 0 && j == 2 && t == 1) {
                            System.out.println("f");
                        }
                        int left = dp[i][t];
                        int right = dp[t + 1][j];
                        int lm = max[i][t];
                        int rm = max[t + 1][j];
                        dp[i][j] = Math.min(dp[i][j], lm * rm + left + right);
                        if (dp[i][j] == 36) System.out.println("i=" + i + ",j=" + j + ",t=" + t);
                    }
                }
            }
        }
        return dp[0][n - 1];
    }
}
