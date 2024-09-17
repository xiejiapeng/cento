package sulqn;

/*
给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。

一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。

例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 */

import java.util.HashMap;
import java.util.Map;

public class L1143 {
    public static void main(String[] args) {
        System.out.println(new L1143().longestCommonSubsequence("abcde", "ace"));
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        int[][] ans = new int[n][m];

        for (int i = 0; i < n; i++) {
            Map<Character, Integer> idx = new HashMap<>();
            for (int j = 0; j < m; j++) {
                idx.put(text2.charAt(j), j);

                if (idx.containsKey(text1.charAt(i))) {
                    int id = idx.get(text1.charAt(i));
                    ans[i][j] = Math.max(ans[i][j], (i - 1 >= 0 && id - 1 >= 0 ? 1 + ans[i - 1][id - 1] : 1));
                }
                if (i - 1 >= 0) {
                    ans[i][j] = Math.max(ans[i][j], ans[i - 1][j]);
                }

            }
        }
//        for (int i = 0; i < n; i++){
//            System.out.println(Arrays.toString(ans[i]));
//        }
        return ans[n - 1][m - 1];
    }
}
