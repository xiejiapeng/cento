package sulwish;

/*
给定 pushed 和 popped 两个序列，每个序列中的 值都不重复，只有当它们可能是在最初空栈上进行的推入
 push 和弹出 pop 操作序列的结果时，返回 true；否则，返回 false 。

 [1,2,3,4,5]
 [4,5,3,2,1]
 [4,3,5,1,2]
 */

import java.util.Stack;

public class L940 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int left = 0;
        int right = 0;
        Stack<Integer> stack = new Stack<>();
        while (right < popped.length) {
            int cur = popped[right];
            if(!stack.isEmpty() && stack.peek() == cur){
                stack.pop();
                right++;
            } else {
                if(left >= pushed.length)return false;
                else {
                    stack.push(pushed[left]);
                    left++;
                }

            }

        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new L940().validateStackSequences(
                new int[]{1,2,3,4,5},
                new int[]{4,3,5,1,2}));
    }
}
