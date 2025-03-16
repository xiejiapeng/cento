package linshen.zhizhen;

/*
给你一个字符串 s ，请找出满足每个字符最多出现两次的最长子字符串，并返回该子字符串的 最大 长度。
2 <= s.length <= 100
s 仅由小写英文字母组成。
 */

public class L3090 {
    public int maximumLengthSubstring(String s) {
        int max = 0;
        int[] cur = new int[26];
        for (int left = 0, right = 0; right < s.length(); right++){
            cur[s.charAt(right) - 'a']++;
            while (!meet(cur)) {
                cur[s.charAt(left)-'a']--;
                left++;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }

    private boolean meet(int[] a) {
        for (int i = 0; i < 26; i++){
            if(a[i] > 2)return false;
        }
        return true;
    }
}
