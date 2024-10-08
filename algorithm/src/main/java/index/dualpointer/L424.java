package index.dualpointer;

/*
给你一个字符串 s 和一个整数 k 。你可以选择字符串中的任一字符，
并将其更改为任何其他大写英文字符。该操作最多可执行 k 次。

在执行上述操作后，返回 包含相同字母的最长子字符串的长度。

1 <= s.length <= 105
s 仅由大写英文字母组成
0 <= k <= s.length
 */

import java.util.Arrays;

public class L424 {
    public int characterReplacement(String s, int k) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < 26; i++){
            char c = (char)('A'+i);
            max = Math.max(max, replace(s, k, c));
        }
        return max;
    }

    public int replace(String s, int k, char c) {
        int n = s.length();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++){
            nums[i] = s.charAt(i) == c ? 0 : 1;
        }
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 0, j = 0; i < n; i++){
            sum += nums[i];
            while (sum > k && j < i) {
                sum -= nums[j];
                j++;
            }
            if(sum <= k) {
                max = Math.max(max, i-j+1);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new L424().characterReplacement("ABAB", 2));
    }
}
