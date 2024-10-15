package index.seqdp;

/*
给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。

每步 可以删除任意一个字符串中的一个字符。
 */

import java.util.Arrays;

public class L583 {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] f = new int[n+1][m+1];
        for (int i = n; i > -1; i--){
            for (int j = m; j > -1; j--){
                if(i == n || j == m) {
                    if(i == n)f[i][j] = m - j;
                    else if(j == m)f[i][j] = n - i;
                } else {
                    if(word1.charAt(i) == word2.charAt(j)) {
                        f[i][j] = f[i+1][j+1];
                    } else {
                        f[i][j] = 1 + Math.min(f[i+1][j], f[i][j+1]);
                    }
                }

            }
        }
        for (int i = 0; i < n; i++){
            System.out.println(Arrays.toString(f[i]));
        }
        return f[0][0];
    }

    public static void main(String[] args) {
        System.out.println(new L583().minDistance("mart","karma"));
    }
}
