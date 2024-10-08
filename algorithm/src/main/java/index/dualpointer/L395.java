package index.dualpointer;

/*
给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。

如果不存在这样的子字符串，则返回 0。

1 <= s.length <= 104
s 仅由小写英文字母组成
1 <= k <= 105
 */

import java.util.HashSet;
import java.util.Set;

public class L395 {
    public int longestSubstring(String s, int k) {
        int[] a = new int[26];
        for (int i = 0; i < s.length(); i++){
            a[s.charAt(i)-'a']++;
        }
        Set<Character> cs = new HashSet<>();
        for (int i = 0; i < 26; i++){
            if(a[i] > 0 && a[i] < k){
                cs.add((char)('a' + i));
            }
        }
        if(cs.isEmpty()) return s.length();

        int max = 0;
        int last = -1;
        for (int i = 0; i < s.length(); i++){
            if(cs.contains(s.charAt(i))) {
                max = Math.max(max, longestSubstring(s.substring(last+1, i), k));
                last = i;
            }
        }
        //todo until the end
        max = Math.max(max, longestSubstring(s.substring(last+1), k));
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new L395().longestSubstring("aaabbbcdefcdefgggggggggggggggcde", 3));
    }
}
