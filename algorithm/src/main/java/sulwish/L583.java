package sulwish;

/*
给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。

每步 可以删除任意一个字符串中的一个字符。
 */


public class L583 {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[][] ans = new int[n + 1][m + 1];

        for (int i = n; i > -1; i--) {
            for (int j = m; j > -1; j--) {
                if (i == n) {
                    ans[i][j] = m - j;
                } else if (j == m) {
                    ans[i][j] = n - i;
                } else {
                    ans[i][j] = Math.min(1 + ans[i][j + 1], 1 + ans[i + 1][j]);
                    if (word1.charAt(i) == word2.charAt(j)) {
                        ans[i][j] = Math.min(ans[i][j], ans[i + 1][j + 1]);
                    }
                }
            }
        }
        return ans[0][0];
    }
}
