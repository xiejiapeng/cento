package linshen.structure;

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
                    if(s.charAt(i) == '(' && sum[j+1] - m == 0)max = Math.max(max, j - i + 1);
                }
            } else {
                int t = n - 1;
                for (int j = t; j > i; j--){
                    if(s.charAt(i) == '(' && sum[j+1] - m == 0)max = Math.max(max, j - i + 1);
                }
            }
            stack.add(i);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new L32().longestValidParentheses(")("));
    }
}
