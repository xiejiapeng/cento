package sulwish;

/*
给你一个二进制字符串数组 strs 和两个整数 m 和 n 。

请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。

如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 */

public class L474 {
    public int findMaxForm(String[] strs, int m, int n) {
        int s = strs.length;
        int[] ones = new int[s];
        int[] zeros = new int[s];

        for (int i = 0; i < s; i++) {
            String x = strs[i];
            int one = 0;
            int zero = 0;
            for (int j = 0; j < x.length(); j++) {
                if (x.charAt(j) == '0') {
                    zero++;
                } else {
                    one++;
                }
            }
            ones[i] = one;
            zeros[i] = zero;
        }

        int[][][] f = new int[s][m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int t = 0; t < s; t++) {
                    if (i == 0 && j == 0) {
                        f[t][i][j] = 0;
                    } else {
                        if (t == 0) {
                            if (zeros[t] <= i && ones[t] <= j) {
                                f[t][i][j] = 1;
                            } else {
                                f[t][i][j] = 0;
                            }
                        } else {
                            f[t][i][j] = f[t - 1][i][j];
                            if (zeros[t] <= i && ones[t] <= j) {
                                f[t][i][j] = Math.max(f[t][i][j], 1 + f[t - 1][i - zeros[t]][j - ones[t]]);
                            }
                        }
                    }

                }
            }
        }
        return f[s - 1][m][n];
    }
}
