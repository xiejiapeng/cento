package linshen.structure.stack;

/*
给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
提示：
0 <= s.length <= 3 * 104
s[i] 为 '(' 或 ')'
 */

import java.util.Stack;

public class L32 {
    public int longestValidParentheses(String s) {
        int n = s.length();
        int[] sum = new int[n+1];
        for (int i = 1; i <= n; i++){
            sum[i] = sum[i-1] + (s.charAt(i-1) == '(' ? 1 : -1);
        }
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i > -1; i--) {

            int m = sum[i];
            //find the nearest j that sum[j+1] < m
            while (!stack.isEmpty() && sum[stack.peek()+1] >= m) {
                stack.pop();
            }
            if(!stack.isEmpty()) {
                int t = stack.peek() - 1;
                for (int j = t; j > i; j--){
                    if(sum[j+1]==m) {
                        max = Math.max(max, j - i + 1);
                        break;
                    }
                }
            } else {
                int t = n - 1;
                for (int j = t; j > i; j--){
                    if(sum[j+1]==m) {
                        max = Math.max(max, j - i + 1);
                        break;
                    }
                }
            }

            stack.add(i);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new L32().longestValidParentheses("(())("));
    }
}
