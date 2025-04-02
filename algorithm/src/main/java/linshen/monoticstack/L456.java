package linshen.monoticstack;

/*
给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。

如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
n == nums.length
1 <= n <= 2 * 105
-109 <= nums[i] <= 109
 */

import java.util.Stack;

public class L456 {
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        int[] leftSmall = new int[n];
        leftSmall[0] = -1;
        for (int i = 1; i < n; i++){
            if(leftSmall[i-1] == -1) {
                leftSmall[i] = i-1;
            } else {
                int u = leftSmall[i-1];
                if(nums[u] <= nums[i-1])leftSmall[i] = u;
                else leftSmall[i] = i - 1;
            }
        }
        Stack<Integer> stack = new Stack<>();
        int[] leftLarge = new int[n];
        for (int i = 0; i < n; i++){
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i])stack.pop();
            if(stack.isEmpty()) {
                leftLarge[i] = -1;
            } else {
                leftLarge[i] = stack.peek();
            }
            stack.add(i);
        }
        for (int i = 0; i < n; i++){
            if(i >= 2) {
                int u = leftLarge[i];
                if(u != -1) {
                    int v = leftSmall[u];
                    if(v != -1 && nums[v] < nums[i]){
                        System.out.println("i="+i+",u="+u+",v="+v);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new L456().find132pattern(new int[]{1,0,1,-4,-3}));
    }
}
