package linshen.slide;

/*
给你一个字符串 s 和一个整数 k，在 s 的所有子字符串中，请你统计并返回 至少有一个 字符 至少出现 k 次的子字符串总数。

子字符串 是字符串中的一个连续、 非空 的字符序列。
1 <= s.length <= 3000
1 <= k <= s.length
s 仅由小写英文字母组成。
 */

import java.util.Arrays;

public class L3325 {
    public int numberOfSubstrings(String s, int k) {
        int n = s.length();
        int[] cnt = new int[26];
        int ans = 0;
        for (int left = 0, right = 0; right < n; right++) {
            cnt[s.charAt(right) - 'a']++;
            if(Arrays.stream(cnt).allMatch(x -> x < k))continue;
            else {
                while (can(cnt, s.charAt(left), k)) {
                    cnt[s.charAt(left)-'a']--;
                    left++;
                }
                ans += (left + 1);
            }
        }
        return ans;
    }

    private boolean can(int[] cnt, char c, int k) {
        int[] tmp = cnt.clone();
        tmp[c-'a']--;
        return Arrays.stream(tmp).anyMatch(x -> x >= k);
    }

}
