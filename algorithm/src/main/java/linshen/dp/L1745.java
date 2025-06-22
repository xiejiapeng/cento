package linshen.dp;

/*
给你一个字符串 s ，如果可以将它分割成三个 非空 回文子字符串，那么返回 true ，否则返回 false 。
当一个字符串正着读和反着读是一模一样的，就称其为 回文字符串 。
提示：

3 <= s.length <= 2000
 */

public class L1745 {
    public boolean checkPartitioning(String s) {
        int n = s.length();
        boolean[][] f = new boolean[n][n];
        for (int len = 1; len <= n; len++){
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if(len == 1)f[i][j] = true;
                else if(len == 2)f[i][j] = (s.charAt(i) == s.charAt(j));
                else {
                    f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i+1][j-1];
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 2; j < n; j++) {
                if(f[0][i] && f[i+1][j-1] && f[j][n-1]) return true;
            }
        }
        return false;
    }
}
