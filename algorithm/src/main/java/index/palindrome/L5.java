package index.palindrome;

/*
给你一个字符串 s，找到 s 中最长的
回文

子串
。
 */

public class L5 {
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] is = new boolean[n][n];
        int max = -1;
        String ans = "";
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    is[i][j] = true;
                } else if (len == 2) {
                    is[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    is[i][j] = (s.charAt(i) == s.charAt(j)) && is[i + 1][j - 1];
                }

                if (is[i][j]) {
                    if (len > max) {
                        ans = s.substring(i, j + 1);
                        max = len;
                    }
                }
            }
        }
        return ans;
    }
}
