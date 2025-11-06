package linshen.structure.stack;

/*
给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：

() 得 1 分。
AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
(A) 得 2 * A 分，其中 A 是平衡括号字符串。
提示：

S 是平衡括号字符串，且只含有 ( 和 ) 。
2 <= S.length <= 50
 */

import java.util.Stack;

public class L856 {
    public int scoreOfParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        Stack<Integer> scores = new Stack<>();
        int ans = 0;
        for (int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(') {
                stack.add('(');
                scores.add(0);
            } else {
                stack.pop();
                int x = scores.pop();
                if(x == 0){
                    x += 1;
                } else {
                    x *= 2;
                }
                if(scores.isEmpty()){
                    ans += x;
                }
                if(!scores.isEmpty()) {
                    int y = scores.pop();
                    y += x;
                    scores.add(y);
                }

            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new L856().scoreOfParentheses("(())"));
    }
}
