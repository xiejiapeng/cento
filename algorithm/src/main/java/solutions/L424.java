package solutions;

/*
给你一个字符串 s 和一个整数 k 。你可以选择字符串中的任一字符，并将其更改为任何其他大写英文字符。该操作最多可执行 k 次。

在执行上述操作后，返回包含相同字母的最长子字符串的长度。

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/longest-repeating-character-replacement
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L424 {
    public int characterReplacement(String s, int k) {
        int max = 1;
        for(int i = 0; i < 26; i++){
            char c = (char)('A' + i);
            int bad = 0;
            for(int left = 0,right = 0; right < s.length(); right++){
                if(s.charAt(right) != c){
                    bad++;
                }
                if(bad > k) {
                    while (left < right && bad > k){
                        if(s.charAt(left) != c)bad--;
                        left++;
                    }
                }
                max = Math.max(max, right - left + 1);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new L424().characterReplacement("ABAA", 0));
    }
}
