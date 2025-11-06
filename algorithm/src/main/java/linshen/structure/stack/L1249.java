package linshen.structure.stack;

/*
给你一个由 '('、')' 和小写字母组成的字符串 s。

你需要从字符串中删除最少数目的 '(' 或者 ')' （可以删除任意位置的括号)，使得剩下的「括号字符串」有效。

请返回任意一个合法字符串。

有效「括号字符串」应当符合以下 任意一条 要求：

空字符串或只包含小写字母的字符串
可以被写作 AB（A 连接 B）的字符串，其中 A 和 B 都是有效「括号字符串」
可以被写作 (A) 的字符串，其中 A 是一个有效的「括号字符串」
 */

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class L1249 {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> rm = new HashSet<>();
        for (int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(') {
                stack.add(i);
            } else if(s.charAt(i) == ')') {
                if(!stack.isEmpty())stack.pop();
                else {
                    rm.add(i);
                }
            }
        }
        while (!stack.isEmpty()){
            rm.add(stack.pop());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++){
            if(!rm.contains(i))sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}
