package linshen.slide;

/*
给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的 排列。如果是，返回 true ；否则，返回 false 。

换句话说，s1 的排列之一是 s2 的 子串 。
 */

public class L567 {
    public boolean checkInclusion(String s1, String s2) {
        int[] a = new int[26];
        for (char c : s1.toCharArray()) {
            a[c-'a']++;
        }
        int[] cur = new int[26];
        int k = s1.length();
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
                if(ide)return true;
            }
        }
        return false;
    }
}
