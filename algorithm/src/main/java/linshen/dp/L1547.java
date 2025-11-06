package linshen.dp;

/*
有一根长度为 n 个单位的木棍，棍上从 0 到 n 标记了若干位置。例如，长度为 6 的棍子可以标记如下：


给你一个整数数组 cuts ，其中 cuts[i] 表示你需要将棍子切开的位置。

你可以按顺序完成切割，也可以根据需要更改切割的顺序。

每次切割的成本都是当前要切割的棍子的长度，切棍子的总成本是历次切割成本的总和。对棍子进行切割将会把一根木棍分成两根较小的木棍（这两根木棍的长度和就是切割前木棍的长度）。请参阅第一个示例以获得更直观的解释。

返回切棍子的 最小总成本
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class L1547 {
    public int minCost(int n, int[] cuts) {
        Arrays.sort(cuts);
        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();
        int m = cuts.length;
        for (int i = 0; i < m; i++) {
            if (i == 0) {
                left.put(i, 0);
            } else {
                left.put(i, cuts[i - 1]);
            }

            if (i == m - 1) {
                right.put(i, n);
            } else {
                right.put(i, cuts[i + 1]);
            }
        }

        int[][] dp = new int[m][m];
        for (int len = 1; len <= m; len++) {
            for (int i = 0; i + len - 1 < m; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    int l = left.get(i);
                    int r = right.get(i);
                    dp[i][j] = r - l;
                } else {
                    int l = left.get(i);
                    int r = right.get(j);
                    int s = r - l;
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int t = i; t <= j; t++) {
                        if (t == i) {
                            dp[i][j] = Math.min(dp[i][j], s + dp[i + 1][j]);
                        } else if (t == j) {
                            dp[i][j] = Math.min(dp[i][j], s + dp[i][j - 1]);
                        } else {
                            dp[i][j] = Math.min(dp[i][j], s + dp[i][t - 1] + dp[t + 1][j]);
                        }
                    }
                }
            }

        }
        return dp[0][m - 1];
    }
}
