package index.seqdp;
/*
给你两个字符串 str1 和 str2，返回同时以 str1 和 str2 作为 子序列 的最短字符串。
如果答案不止一个，则可以返回满足条件的 任意一个 答案。

如果从字符串 t 中删除一些字符（也可能不删除），可以得到字符串 s ，那么 s 就是 t 的一个子序列。

1 <= str1.length, str2.length <= 1000
str1 和 str2 都由小写英文字母组成。
 */

import java.util.Arrays;

public class L1092 {
    public String shortestCommonSupersequence(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int[][] f = new int[n+1][m+1];
        for (int i = n; i >-1; i--){
            for (int j = m; j > -1; j--){
                f[i][j] = Integer.MAX_VALUE - 100;;
                if(i == n && j == m)f[i][j] = 0;
                else if(i == n){
                    f[i][j] = m - j;
                } else if(j == m) {
                    f[i][j] = n - i;
                } else {
                    if(str1.charAt(i) == str2.charAt(j)) {
                        f[i][j] = Math.min(f[i][j], 1 + f[i+1][j+1]);
                    }
                    f[i][j] = Math.min(f[i][j], 1 + f[i+1][j]);
                    f[i][j] = Math.min(f[i][j], 1 + f[i][j+1]);
                }

            }
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int j = 0;
        while (i < n || j < m) {
            if(i == n) {
                sb.append(str2.charAt(j));
                j++;
            } else if(j == m) {
                sb.append(str1.charAt(i));
                i++;
            } else {
                if(str1.charAt(i) == str2.charAt(j)) {
                    if(f[i][j] == 1 + f[i+1][j+1]) {
                        sb.append(str1.charAt(i));
                        i++;
                        j++;
                        continue;
                    }
                }
                if(f[i][j] == 1 + f[i+1][j]) {
                    sb.append(str1.charAt(i));
                    i++;
                } else {
                    sb.append(str2.charAt(j));
                    j++;
                }
            }

        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new L1092().shortestCommonSupersequence("a", "a"));
    }
}
