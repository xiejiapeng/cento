package linshen.greedy;

//给你一个字符串 word。
//
// 返回以 首尾字母相同 且 长度至少为 4 的 不相交子字符串 的最大数量。
//
// 子字符串 是字符串中连续的 非空 字符序列。

import java.util.Arrays;

public class L3557 {
    public int maxSubstrings(String word) {
        int n = word.length();
        int[] pos = new int[26];
        int[] f = new int[n+1];
        Arrays.fill(pos, -1);
        pos[word.charAt(n-1)-'a'] = n - 1;
        for (int i = n - 4; i > -1; i--){
            f[i] = f[i+1];
            if(i == 0){
                System.out.println("f");
            }
            if(pos[word.charAt(i) - 'a'] != -1) {
                int t = pos[word.charAt(i) - 'a'];
                f[i] = Math.max(f[i], 1 + f[1 + t]);
            }
            if(i + 2 < n){
                pos[word.charAt(i+2) - 'a'] = i + 2;
            }
        }
        return f[0];
    }

    public static void main(String[] args) {
        System.out.println(new L3557().maxSubstrings("axxafxxf"));
    }
}
