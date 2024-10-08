package index.dualpointer;

/*
给定一个字符串 s ，请你找出其中不含有重复字符的 最长
子串的长度。
 */

import java.util.HashSet;
import java.util.Set;

public class L3 {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> see = new HashSet<>();
        int max = 1;
        for (int i = 0, j = 0; i < s.length(); i++){
            char cur = s.charAt(i);
            while (see.contains(cur) && j < i) {
                see.remove(s.charAt(j));
                j++;
            }
            max = Math.max(max, i - j + 1);
            see.add(cur);
        }
        return max;
    }
}
