package sulwish;

/*
给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。

如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
 */

import java.util.Stack;

public class L456 {
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        int[] a = new int[n];

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            a[i] = min;

            min = Math.min(min, nums[i]);
        }

        //左侧最近的大于i的序号
        int[] left = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (stack.isEmpty()) {
                stack.add(i);
            } else {
                while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    left[i] = -1;
                } else {
                    left[i] = stack.peek();
                }
                stack.add(i);
            }
        }

        for (int i = 0; i < n; i++) {
            if (left[i] != -1) {
                int l = left[i];
                int m = a[l];
                if (m < nums[i]) return true;
            }
        }

        return false;


    }
}
