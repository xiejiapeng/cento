package linshen.structure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。

整数除法仅保留整数部分。

你可以假设给定的表达式总是有效的。所有中间结果将在 [-231, 231 - 1] 的范围内。

注意：不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
提示：

1 <= s.length <= 3 * 105
s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
s 表示一个 有效表达式
表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
题目数据保证答案是一个 32-bit 整数
3+2*2
 */
public class L227 {
    public int calculate(String s) {
        List<String> all = parse(s);
        Stack<Integer> nums = new Stack<>();
        Stack<String> ops = new Stack<>();
        for (int i = 0; i < all.size(); i++){
            if(i % 2 != 0) {
                ops.add(all.get(i));
            } else {
                if(!ops.isEmpty() && (ops.peek().equals("*") || ops.peek().equals("/"))) {
                    int a = nums.pop();
                    int b = Integer.parseInt(all.get(i));
                    String op = ops.pop();
                    if(op.equals("*"))nums.add(a * b);
                    else nums.add(a / b);
                } else {
                    nums.add(Integer.parseInt(all.get(i)));
                }
            }
        }
        int ans = 0;
        while (!ops.isEmpty()) {
            int x = nums.pop();
            String op = ops.pop();
            if(op.equals("+"))ans += x;
            else ans -= x;
        }
        ans += nums.pop();
        return ans;
    }
    private List<String> parse(String s) {
        int cur = 0;
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*' || s.charAt(i) == '/') {
                ans.add(String.valueOf(cur));
                ans.add("" + s.charAt(i));
                cur = 0;
            } else if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                cur = cur * 10 + (s.charAt(i) - '0');
            }
        }
        ans.add(String.valueOf(cur));
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new L227().calculate("3+2*2"));
    }
}
