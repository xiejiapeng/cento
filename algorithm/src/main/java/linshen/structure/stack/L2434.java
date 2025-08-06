package linshen.structure.stack;

/*
给你一个字符串 s 和一个机器人，机器人当前有一个空字符串 t 。执行以下操作之一，直到 s 和 t 都变成空字符串：

删除字符串 s 的 第一个 字符，并将该字符给机器人。机器人把这个字符添加到 t 的尾部。
删除字符串 t 的 最后一个 字符，并将该字符给机器人。机器人将该字符写到纸上。
请你返回纸上能写出的字典序最小的字符串。

提示：
1 <= s.length <= 105
s 只包含小写英文字母。
 */

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class L2434 {
    public String robotWithString(String s) {
        int n = s.length();
        int[] min = new int[n];
        for (int i = n - 1; i > -1; i--){
            if(i == n - 1)min[i] = i;
            else {
                if(s.charAt(i) <= s.charAt(min[i+1])) {
                    min[i] = i;
                } else {
                    min[i] = min[i+1];
                }
            }
        }
        int start = 0;
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        while (start < n) {
            int t = min[start];
            //todo h first look at the stack
            while (!stack.isEmpty() && s.charAt(stack.peek()) <= s.charAt(t)) {
                sb.append(s.charAt(stack.pop()));
            }
            for (int i = start; i < t; i++){
                stack.add(i);
            }
            sb.append(s.charAt(t));
            start = t + 1;
        }
        while (!stack.isEmpty()) {
            sb.append(s.charAt(stack.pop()));
        }
        return sb.toString();
    }
}
