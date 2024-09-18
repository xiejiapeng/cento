package sulqn;

/*
给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
每个右括号都有一个对应的相同类型的左括号。
 */

import java.util.Stack;

public class L20 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.add(c);
            } else if (c == ')') {
                if (!stack.isEmpty() && stack.peek() == '(') stack.pop();
                else return false;
            } else if (c == ']') {
                if (!stack.isEmpty() && stack.peek() == '[') stack.pop();
                else return false;
            } else {
                if (!stack.isEmpty() && stack.peek() == '{') stack.pop();
                else return false;
            }
        }
        return stack.isEmpty();
    }
}
