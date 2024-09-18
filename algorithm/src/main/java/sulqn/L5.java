package sulqn;

/*
给你一个字符串 s，找到 s 中最长的回文子串。

如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 */

public class L5 {
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] pal = new boolean[n][n];
        String ans = "";
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    pal[i][j] = true;
                } else if (len == 2) {
                    pal[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    if (s.charAt(i) == s.charAt(j)) {
                        pal[i][j] = pal[i + 1][j - 1];
                    }
                }

                if (pal[i][j]) {
                    if (j - i + 1 > ans.length()) {
                        ans = s.substring(i, j + 1);
                    }
                }
            }
        }
        return ans;
    }
}
