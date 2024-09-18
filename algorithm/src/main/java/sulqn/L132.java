package sulqn;

/*
给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是
回文串
。

返回符合要求的 最少分割次数 。
 */

import java.util.Arrays;

public class L132 {
    public static void main(String[] args) {
        System.out.println(new L132().minCut("cdd"));
    }

    public int minCut(String s) {
        int n = s.length();
        boolean[][] isPal = new boolean[n][n];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    isPal[i][j] = true;
                } else {
                    if (len == 2) {
                        isPal[i][j] = s.charAt(i) == s.charAt(j);
                    } else {
                        isPal[i][j] = (s.charAt(i) == s.charAt(j)) && isPal[i + 1][j - 1];
                    }
                }
            }
        }

        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n; i++) {
            if (isPal[0][i]) {
                ans[i] = 0;
            } else {
                for (int j = 0; j <= i; j++) {
                    if (isPal[j][i]) {
                        if (ans[j - 1] != -1) {
                            if (ans[i] == -1) {
                                ans[i] = 1 + ans[j - 1];
                            } else {
                                ans[i] = Math.min(ans[i], 1 + ans[j - 1]);
                            }

                        }
                    }
                }
            }
        }
        return ans[n - 1];
    }
}
