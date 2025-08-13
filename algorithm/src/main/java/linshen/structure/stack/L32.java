package linshen.structure.stack;

/*
给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
提示：
0 <= s.length <= 3 * 104
s[i] 为 '(' 或 ')'
 */

import java.util.Stack;

public class L32 {
    //todo hhhhhh 我的栈为什么总是这么弱...
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        stack.add(-1);
        for (int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(') {
                stack.add(i);
            } else {
                if(stack.isEmpty()) {
                    stack.add(i);
                } else {
                    stack.pop();
                    if(!stack.isEmpty())max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new L32().longestValidParentheses("(())("));
    }
}
