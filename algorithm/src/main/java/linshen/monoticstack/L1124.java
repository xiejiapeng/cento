package linshen.monoticstack;

/*
给你一份工作时间表 hours，上面记录着某一位员工每天的工作小时数。

我们认为当员工一天中的工作小时数大于 8 小时的时候，那么这一天就是「劳累的一天」。

所谓「表现良好的时间段」，意味在这段时间内，「劳累的天数」是严格 大于「不劳累的天数」。

请你返回「表现良好时间段」的最大长度。
 */

import java.util.Stack;

public class L1124 {
    public int longestWPI(int[] hours) {
        int n = hours.length;
        int[] sum = new int[n+1];
        for (int i = 1; i <= n; i++){
            sum[i] = sum[i-1] + (hours[i-1] > 8 ? 1 : -1);
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= n; i++){
            if(stack.isEmpty() || sum[i] < sum[stack.peek()])stack.add(i);
        }
        int max = 0;
        for (int j = n; j > -1; j--){
            while (!stack.isEmpty() && sum[stack.peek()] < sum[j]) {
                max = Math.max(max, j - stack.peek());
                stack.pop();
            }
        }
        return max;
    }
}
