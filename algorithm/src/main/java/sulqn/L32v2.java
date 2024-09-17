package sulqn;

/*
给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号
子串的长度。
 */

import java.util.Stack;

public class L32v2 {
    public static void main(String[] args) {
        String s = "(()(())";
        System.out.println(new L32v2().longestValidParentheses(s));
    }

    public int longestValidParentheses(String s) {
        Stack<int[]> stack = new Stack<>();
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.add(new int[]{i, -1});
            } else {
                if (stack.isEmpty()) {
                    continue;
                } else if (stack.size() == 1 && stack.peek()[1] != -1) {
                    stack.clear();
                } else {
                    if (stack.peek()[1] > 0) {
                        stack.pop();
                    }

                    int[] x = stack.pop();
                    x[1] = i;

                    if (!stack.isEmpty() && stack.peek()[1] > -1) {
                        int[] y = stack.pop();
                        x[0] = y[0];
                    }
                    ans = Math.max(ans, i - x[0] + 1);
                    stack.add(x);
                }

            }
        }
        return ans;
    }
}
