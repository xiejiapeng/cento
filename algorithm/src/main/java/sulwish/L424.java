package sulwish;

/*
给你一个字符串 s 和一个整数 k 。你可以选择字符串中的任一字符，并将其更改为任何其他大写英文字符。该操作最多可执行 k 次。

在执行上述操作后，返回 包含相同字母的最长子字符串的长度。
 */
public class L424 {
    public int characterReplacement(String s, int k) {
        int max = 0;
        for (int i = 0; i < 26; i++){
            //允许的非A的个数
            int x = k;
            for (int left = 0,  right = -1; left < s.length(); ){
                while (right + 1 < s.length() && (s.charAt(right + 1) - 'A' == i || x > 0)) {
                    right++;
                    if(s.charAt(right) - 'A' != i)x--;
                }
                max = Math.max(max, right - left + 1);
                if(s.charAt(left) - 'A' != i)x++;
                left++;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new L424().characterReplacement("ABAA", 0));
    }
}
