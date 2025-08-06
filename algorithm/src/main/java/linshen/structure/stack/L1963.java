package linshen.structure.stack;

/*
给你一个字符串 s ，下标从 0 开始 ，且长度为偶数 n 。字符串 恰好 由 n / 2 个开括号 '[' 和 n / 2 个闭括号 ']' 组成。

只有能满足下述所有条件的字符串才能称为 平衡字符串 ：

字符串是一个空字符串，或者
字符串可以记作 AB ，其中 A 和 B 都是 平衡字符串 ，或者
字符串可以写成 [C] ，其中 C 是一个 平衡字符串 。
你可以交换 任意 两个下标所对应的括号 任意 次数。

返回使 s 变成 平衡字符串 所需要的 最小 交换次数。
][][
 */

import scala.Char;

import java.util.Stack;

public class L1963 {
    public int minSwaps(String s) {
        Stack<Character> stack = new Stack<>();
        int cnt = 0;
        for (int i = 0; i < s.length(); i++){
            if(s.charAt(i) == ']'){
                if(!stack.isEmpty())stack.pop();
                else cnt++;
            } else {
                stack.add('[');
            }
        }
        //todo h cnt是不平衡数，比如]]][[[时cnt=3，此时贪心方法是将最左边和最右边交换，此时能带走4个符号
        if(2 * cnt % 4 == 0)return 2 * cnt / 4;
        else return (2 * cnt-2) / 4 + 1;
    }

    public static void main(String[] args) {
        // [[]] -> [][]
        // ]]][[[ -> []][[]
        // ]]]]]][[[[[[ -> []]]]][[[[[](]]]][[[[) -> []]][[[](]][[) -> [][]
        //
        System.out.println(new L1963().minSwaps("[[][[[[][][[[[]]][][]]]]][[][[][][]][[[[]]][[]][[]][[]]]]]]]][]][]]][[]][[[[[[][[]][[[][]][[]]]["));
    }
}
