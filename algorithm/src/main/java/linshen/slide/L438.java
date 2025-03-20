package linshen.slide;

import java.util.ArrayList;
import java.util.List;

/*
给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 */

public class L438 {
    public List<Integer> findAnagrams(String s2, String s1) {
        int[] a = new int[26];
        for (char c : s1.toCharArray()) {
            a[c-'a']++;
        }
        int[] cur = new int[26];
        int k = s1.length();
        List<Integer> ans = new ArrayList<>();
        for (int right = 0; right < s2.length(); right++){
            int left = right - k + 1;
            cur[s2.charAt(right) - 'a']++;
            if(left - 1 >= 0) {
                cur[s2.charAt(left-1) - 'a']--;
            }
            if(left >= 0) {
                boolean ide = true;
                for (int i = 0; i < 26; i++){
                    if(a[i] != cur[i]){
                        ide = false;
                        break;
                    }
                }
                if(ide){
                    ans.add(left);
                }
            }
        }
        return ans;
    }
}
