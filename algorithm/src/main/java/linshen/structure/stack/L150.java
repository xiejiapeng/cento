package linshen.structure.stack;

/*
给你一个字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式。

请你计算该表达式。返回一个表示表达式值的整数。

注意：

有效的算符为 '+'、'-'、'*' 和 '/' 。
每个操作数（运算对象）都可以是一个整数或者另一个表达式。
两个整数之间的除法总是 向零截断 。
表达式中不含除零运算。
输入是一个根据逆波兰表示法表示的算术表达式。
答案及所有中间计算结果可以用 32 位 整数表示。
输入：tokens = ["2","1","+","3","*"] 输出：9 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;

public class L150 {
    public int evalRPN(String[] tokens) {
        Map<String, BiFunction<Integer, Integer, Integer>> ops = new HashMap<>();
        ops.put("+", (a,b) -> (a+b));
        ops.put("-", (a,b) -> (a-b));
        ops.put("*", (a,b) -> (a*b));
        ops.put("/", (a,b) -> (a/b));
        Stack<Integer> nums = new Stack<>();
        for (int i = 0; i < tokens.length; i++){
            if(ops.containsKey(tokens[i])) {
                int b = nums.pop();
                int a = nums.pop();
                nums.add(ops.get(tokens[i]).apply(a, b));
            } else {
                nums.add(Integer.parseInt(tokens[i]));
            }
        }
        return nums.pop();
    }
}
