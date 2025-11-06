package linshen.structure.stack;

/*
给你一个字符串 s 和两个整数 x 和 y 。你可以执行下面两种操作任意次。
删除子字符串 "ab" 并得到 x 分。
删除子字符串 "ba" 并得到 y 分。
请返回对 s 字符串执行上面操作若干次能得到的最大得分。
提示：

1 <= s.length <= 105
1 <= x, y <= 104
s 只包含小写英文字母。

 */

import java.util.Stack;

public class L1717 {
    public int maximumGain(String s, int x, int y) {
        //todo hh 这题失败了
        Stack<Character> stack = new Stack<>();
        int score = 0;
        int n = s.length();
        for (int i = 0; i < n; i++){
            char c = s.charAt(i);
            if(c == 'a') {
                if(stack.isEmpty() || stack.peek() != 'b')stack.add(c);
                else if(stack.peek() == 'b') {
                    if(y >= x) {
                        stack.pop();
                        score += y;
                    } else {
                        if(i+1<n&&s.charAt(i+1)=='b'){
                            stack.add(c);
                        } else {
                            stack.pop();
                            score += y;
                        }
                    }
                }
            } else if(c == 'b') {
                if(stack.isEmpty() || stack.peek() != 'a')stack.add(c);
                else if(stack.peek() == 'a') {
                    if(x >= y) {
                        stack.pop();
                        score += x;
                    } else {
                        if(i+1<n&&s.charAt(i+1)=='a'){
                            stack.add(c);
                        } else {
                            stack.pop();
                            score += x;
                        }
                    }
                }
            } else {
                stack.add(c);
            }
        }
        return score;
    }

    public static void main(String[] args) {
        System.out.println(new L1717().maximumGain("aabbaaxybbaabb", 5,4));
    }
}
