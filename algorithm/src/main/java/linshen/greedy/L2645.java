package linshen.greedy;

/*
给你一个字符串 word ，你可以向其中任何位置插入 "a"、"b" 或 "c" 任意次，返回使 word 有效 需要插入的最少字母数。

如果字符串可以由 "abc" 串联多次得到，则认为该字符串 有效 。
abcabcxxxx
提示：
1 <= word.length <= 50
word 仅由字母 "a"、"b" 和 "c" 组成。
 */

public class L2645 {
    public int addMinimum(String word) {
        int next = 0;
        int cnt = 0;
        for (int i = 0; i < word.length(); i++){
            if(word.charAt(i) - 'a' != next) {
                cnt++;
                next = (next + 1) % 3;
                i--;
            } else {
                next = (next + 1) % 3;
            }
        }
        if(next != 0) {
            cnt += (3 - next);
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(new L2645().addMinimum("b"));
    }
}
