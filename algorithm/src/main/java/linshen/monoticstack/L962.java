package linshen.monoticstack;

/*
给定一个整数数组 A，坡是元组 (i, j)，其中 i < j 且 A[i] <= A[j]。这样的坡的宽度为 j - i。

找出 A 中的坡的最大宽度，如果不存在，返回 0 。
2 <= A.length <= 50000
0 <= A[i] <= 50000
todo 不能二分，没有单调性
todo 单调栈，高点的取值没有单调性，但是低点是有的，从左到右，新遍历点左边的值如果比当前值小，则当前值不能加进来
todo h hhh 最高优先级的模板题
 */

import java.util.Stack;

public class L962 {
    public int maxWidthRamp(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++){
            if(stack.isEmpty() || nums[stack.peek()] > nums[i])stack.add(i);
        }
        int max = -1;
        for (int i = nums.length - 1; i > -1; i--){
            while (!stack.isEmpty()) {
                int t = stack.peek();
                if(nums[t] > nums[i])break;
                else {
                    max = Math.max(max, i - t);
                    stack.pop();
                }
            }
        }
        return max;
    }


}
