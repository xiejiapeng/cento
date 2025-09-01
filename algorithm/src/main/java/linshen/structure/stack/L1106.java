package linshen.structure.stack;

/*
布尔表达式 是计算结果不是 true 就是 false 的表达式。有效的表达式需遵循以下约定：

't'，运算结果为 true
'f'，运算结果为 false
'!(subExpr)'，运算过程为对内部表达式 subExpr 进行 逻辑非（NOT）运算
'&(subExpr1, subExpr2, ..., subExprn)'，运算过程为对 2 个或以上内部表达式 subExpr1, subExpr2, ..., subExprn 进行 逻辑与（AND）运算
'|(subExpr1, subExpr2, ..., subExprn)'，运算过程为对 2 个或以上内部表达式 subExpr1, subExpr2, ..., subExprn 进行 逻辑或（OR）运算
给你一个以字符串形式表述的 布尔表达式 expression，返回该式的运算结果。

题目测试用例所给出的表达式均为有效的布尔表达式，遵循上述约定。
提示：

1 <= expression.length <= 2 * 104
expression[i] 为 '('、')'、'&'、'|'、'!'、't'、'f' 和 ',' 之一

输入：expression = "!(&(f,t))" 输出：true
 */


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class L1106 {
    //todo h 秒了挺意外的；记住ops表示当前的op，nums不断塞入操作数，遇到)表示op结束了，需要进行计算，再塞入nums给之前的op
    public boolean parseBoolExpr(String expression) {
        Stack<Character> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();
        for (int i = 0; i < expression.length(); i++){
            if(expression.charAt(i) == 'f') {
                nums.add('f');
            } else if(expression.charAt(i) == 't') {
                nums.add('t');
            } else if(expression.charAt(i) == '&') {
                ops.add('&');
                i++;
                nums.add('x');
            } else if(expression.charAt(i) == '|') {
                ops.add('|');
                i++;
                nums.add('x');
            } else if(expression.charAt(i) == '!') {
                ops.add('!');
                i++;
                nums.add('x');
            } else if(expression.charAt(i) == ',') {
                continue;
            } else if(expression.charAt(i) == ')'){
                char op = ops.pop();
                if(op == '&') {
                    boolean one = true;
                    while (nums.peek() != 'x') {
                        char c = nums.pop();
                        if(c == 't')one &= true;
                        else one &= false;
                    }
                    nums.pop();
                    nums.add(one?'t':'f');
                } else if(op == '|') {
                    boolean one = false;
                    while (nums.peek() != 'x') {
                        char c = nums.pop();
                        if(c == 't')one |= true;
                        else one |= false;
                    }
                    nums.pop();
                    nums.add(one?'t':'f');
                } else if(op == '!') {
                    char c = nums.pop();
                    nums.pop();
                    if(c == 't')nums.add('f');
                    else nums.add('t');
                }
            }
        }

        return nums.pop() == 't';
    }

    public static void main(String[] args) {
        System.out.println(new L1106().parseBoolExpr("&(|(f))"));
    }

}
