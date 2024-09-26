package index.monostack;

/*
给你一个整数数组 nums 和两个整数：left 及 right 。找出 nums 中连续、非空且其中最大
元素在范围 [left, right] 内的子数组，并返回满足条件的子数组的个数。

生成的测试用例保证结果符合 32-bit 整数范围。
 */

import java.util.Arrays;
import java.util.Stack;

public class L795 {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int n = nums.length;
        int[] l = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++){
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                stack.pop();
            }
            l[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.add(i);
        }
        stack.clear();

        int[] r = new int[n];
        for (int i = n - 1; i > -1; i--){
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                stack.pop();
            }
            r[i] = stack.isEmpty() ? n : stack.peek();
            stack.add(i);
        }
        System.out.println(Arrays.toString(l));
        System.out.println(Arrays.toString(r));
        int ans = 0;
        for (int i = 0; i < n; i++){
            if(nums[i] >= left && nums[i] <= right) {
                int x = i - l[i];
                int y = r[i] - i;
                ans += x * y;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new L795().numSubarrayBoundedMax(new int[]{73,55,36,5,55,14,9,7,72,52}, 32,69));
    }
}
