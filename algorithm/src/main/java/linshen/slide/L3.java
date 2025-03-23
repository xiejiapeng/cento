package linshen.slide;

import java.util.HashMap;
import java.util.Map;

/*
给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。

0 <= s.length <= 5 * 104
s 由英文字母、数字、符号和空格组成
 */
public class L3 {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> cnt = new HashMap<>();
        int max = 0;
        for (int left = 0, right = 0; right < s.length(); right++){
            while (cnt.containsKey(s.charAt(right))) {
                cnt.put(s.charAt(left), cnt.get(s.charAt(left)) - 1);
                if(cnt.get(s.charAt(left)) == 0) {
                    cnt.remove(s.charAt(left));
                }
                left++;
            }
            cnt.put(s.charAt(right), cnt.getOrDefault(s.charAt(right), 0) + 1);
            max = Math.max(max, (right - left + 1));
        }
        return max;
    }
}
