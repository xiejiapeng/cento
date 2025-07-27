package linshen.structure;

/*
给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列
由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足：i < j < k
和 nums[i] < nums[k] < nums[j] 。
如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
 */

import java.util.Stack;

public class L456 {
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        left[0] = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++){
            left[i] = Math.min(left[i-1], nums[i-1]);
        }
        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        int[] nearest = new int[n];
        for (int i = 1; i < n; i++){
            nearest[i] = -1;
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i])stack.pop();
            if(!stack.isEmpty()) {
                nearest[i] = stack.peek();
            }
            stack.add(i);
        }
        for (int i = 1; i < n; i++){
            int t = nearest[i];
            if(t != -1) {
                int s = left[t];
                if(s < nums[i])return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new L456().find132pattern(new int[]{-2,1,1}));
    }

}
