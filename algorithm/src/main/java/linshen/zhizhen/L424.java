package linshen.zhizhen;

/*
给你一个字符串 s 和一个整数 k 。你可以选择字符串中的任一字符，
并将其更改为任何其他大写英文字符。该操作最多可执行 k 次。
在执行上述操作后，返回 包含相同字母的最长子字符串的长度。

1 <= s.length <= 105
s 仅由大写英文字母组成
0 <= k <= s.length
 */

public class L424 {
    public int characterReplacement(String s, int k) {
        int n = s.length();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < 26; i++){
            int cur = 0;
            for (int left = 0, right = 0; right < n; right++){
                if(s.charAt(right) - 'A' != i)cur++;
                while (cur > k) {
                    if(s.charAt(left) - 'A' != i)cur--;
                    left++;
                }
                max = Math.max(max, right - left + 1);
            }
        }
        return max;
    }
}
