package linshen.slide;

/*
给定一个二进制字符串 s 和一个正整数 n，如果对于 [1, n] 范围内的每个整数，其二进制表示都是 s 的 子字符串 ，就返回 true，否则返回 false 。

1011
0011


子字符串 是字符串中连续的字符序列。
 */

import java.util.HashSet;
import java.util.Set;

public class L1016 {
    //todo O(n)解法需要根据1和n的值确定
    public boolean queryString(String s, int n) {
        int m = s.length();
        int[][] all = new int[m][m];
        Set<Integer> a = new HashSet<>();
        for (int len = 1; len <= 32; len++) {
            for (int i = 0; i + len - 1 < m; i++){
                int j = i + len - 1;
                if(len == 1) {
                    all[i][j] = s.charAt(i) - '0';
                } else {
                    all[i][j] = all[i][j-1] * 2 + (s.charAt(j) - '0');
                }
                if(all[i][j] >= 1 && all[i][j] <= n)a.add(all[i][j]);
            }
        }
        return a.size() == n;
    }

    public static void main(String[] args) {
        System.out.println(new L1016().queryString("0110", 3));
    }
}
