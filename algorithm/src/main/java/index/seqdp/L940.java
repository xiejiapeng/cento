package index.seqdp;

/*
给定一个字符串 s，计算 s 的 不同非空子序列 的个数。因为结果可能很大，所以返回答案需要对 10^9 + 7 取余 。

字符串的 子序列 是经由原字符串删除一些（也可能不删除）字符但不改变剩余字符相对位置的一个新字符串。

例如，"ace" 是 "abcde" 的一个子序列，但 "aec" 不是。

1 <= s.length <= 2000
s 仅由小写英文字母组成
 */

import java.util.Arrays;

public class L940 {
    int mod = (int)(1e9+7);
    public int distinctSubseqII(String s) {
        int n = s.length();
        long[] f = new long[n];
        int[] pos = new int[26];
        Arrays.fill(pos, -1);
        for (int i = 0; i < n; i++){
            if(i == 0)f[i] = 1;
            else {
                f[i] = 1;
                for (int j = 0; j < 26; j++){
                    if(pos[j] != -1) {
                        f[i] += f[pos[j]];
                        f[i] %= mod;
                    }
                }
            }
            pos[s.charAt(i) - 'a'] = i;
        }
        long ans = 0;
        for (int i = 0; i < 26; i++){
            if(pos[i] != -1)ans += f[pos[i]];
            ans %= mod;
        }
        return (int)(ans % mod);
    }
}
