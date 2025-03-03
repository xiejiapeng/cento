package index.seqdp;

/*
给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。

一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。

例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 */

import java.util.Arrays;

public class L1143 {
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] f = new int[n][m];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if(text1.charAt(i) == text2.charAt(j)) {
                    f[i][j] = Math.max(f[i][j], 1 + ((i-1<0||j-1<0)?0:f[i-1][j-1]));
                }
                if(i-1>=0){
                    f[i][j] = Math.max(f[i][j], f[i-1][j]);
                }
                if(j-1>=0){
                    f[i][j] = Math.max(f[i][j], f[i][j-1]);
                }
            }
        }
        for (int i = 0; i < n; i++){
            System.out.println(Arrays.toString(f[i]));
        }

        return f[n-1][m-1];
    }

    public static void main(String[] args) {
        System.out.println(new L1143().longestCommonSubsequence("ab","a"));
    }
}
