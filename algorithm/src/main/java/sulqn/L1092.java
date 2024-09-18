package sulqn;

/*
给你两个字符串 str1 和 str2，返回同时以 str1 和 str2 作为 子序列 的最短字符串。如果答案不止一个，则可以返回满足条件的 任意一个 答案。

如果从字符串 t 中删除一些字符（也可能不删除），可以得到字符串 s ，那么 s 就是 t 的一个子序列。
 */

import java.util.HashSet;
import java.util.Set;

public class L1092 {
    public int shortestCommonSupersequence(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        int[][] ans = new int[n][m];

        Set<Character> is = new HashSet<>();
        for (int i = 0; i < n; i++) {
            is.add(str1.charAt(i));
            Set<Character> js = new HashSet<>();
            for (int j = 0; j < m; j++) {
                js.add(str2.charAt(j));

                ans[i][j] = i + j + 2;
                if (str1.charAt(i) == str2.charAt(j)) {
                    if (i - 1 < 0 && j - 1 < 0) {
                        ans[i][j] = Math.min(ans[i][j], 1);
                    } else if (i - 1 < 0) {
                        ans[i][j] = Math.min(ans[i][j], j + 1);
                    } else if (j - 1 < 0) {
                        ans[i][j] = Math.min(ans[i][j], i + 1);
                    } else {
                        ans[i][j] = Math.min(ans[i][j], 1 + ans[i - 1][j - 1]);
                    }

                }

                if (i - 1 < 0) {
                    if (js.contains(str1.charAt(i))) {
                        ans[i][j] = Math.min(ans[i][j], j + 1);
                    } else {
                        ans[i][j] = Math.min(ans[i][j], j + 2);
                    }

                } else {
                    ans[i][j] = Math.min(ans[i][j], ans[i - 1][j] + 1);
                }

                if (j - 1 < 0) {
                    if (is.contains(str2.charAt(j))) {
                        ans[i][j] = Math.min(ans[i][j], i + 1);
                    } else {
                        ans[i][j] = Math.min(ans[i][j], i + 2);
                    }

                } else {
                    ans[i][j] = Math.min(ans[i][j], ans[i][j - 1] + 1);
                }
            }
        }
        return ans[n - 1][m - 1];
    }
}
