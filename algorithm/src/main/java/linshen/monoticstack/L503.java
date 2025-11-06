package linshen.monoticstack;

/*
给定一个循环数组 nums （ nums[nums.length - 1] 的下一个元素是 nums[0] ），返回 nums 中每个元素的 下一个更大元素 。

数字 x 的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1 。
 */

import java.util.Stack;

public class L503 {
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        for (int i = n - 1; i > -1; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) stack.pop();
            stack.add(nums[i]);
        }
        int[] ans = new int[n];
        for (int i = n - 1; i > -1; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) stack.pop();
            if (stack.isEmpty()) ans[i] = -1;
            else ans[i] = stack.peek();
            stack.add(nums[i]);
        }
        return ans;
    }
}
