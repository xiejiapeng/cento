package linshen.structure;

/*
给你一个字符串 s ，请你返回满足以下条件的最长子字符串的长度：
每个元音字母，即 'a'，'e'，'i'，'o'，'u' ，在子字符串中都恰好出现了偶数次。
提示：
1 <= s.length <= 5 x 10^5
s 只包含小写英文字母。
 */

import java.util.HashMap;
import java.util.Map;

public class L1371 {
    /*
    x x x x x
     */
    public int findTheLongestSubstring(String s) {
        int n = s.length();
        int[] sum = new int[n+1];
        Map<Integer, Integer> see = new HashMap<>();
        see.put(0, 0);
        int max = 0;
        for (int i = 1; i <= n; i++){
            if(s.charAt(i-1) == 'a') {
                sum[i] = sum[i-1] ^ (1 << 4);
            } else if(s.charAt(i-1) == 'e') {
                sum[i] = sum[i-1] ^ (1 << 3);
            } else if(s.charAt(i-1) == 'i') {
                sum[i] = sum[i-1] ^ (1 << 2);
            } else if(s.charAt(i-1) == 'o') {
                sum[i] = sum[i-1] ^ (1 << 1);
            } else if(s.charAt(i-1) == 'u') {
                sum[i] = sum[i-1] ^ 1;
            } else {
                sum[i] = sum[i-1];
            }
//            System.out.println(Integer.toBinaryString(sum[i]));
            //i = 0
            if(see.containsKey(sum[i])) {
                int t = see.get(sum[i]);
                max = Math.max(max, i - t);
            } else {
                see.putIfAbsent(sum[i], i);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new L1371().findTheLongestSubstring("bcbcbc"));
    }
}
