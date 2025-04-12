package linshen.monoticstack;

/*
给你一个 正 整数数组 nums 。
请你求出 nums 中有多少个子数组，满足子数组中 第一个 和 最后一个 元素都是这个子数组中的 最大 值。
1 <= nums.length <= 105
1 <= nums[i] <= 109
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class L3113 {
    public long numberOfSubarrays(int[] nums) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        Map<Integer,Long> map = new HashMap<>();
        long cnt = 0;
        for (int i = n - 1; i > -1; i--){
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i])stack.pop();
            if(stack.isEmpty() || nums[stack.peek()] > nums[i]) {
                map.put(nums[i], 1l);
            } else {
                map.put(nums[i], map.get(nums[i]) + 1);
            }
            cnt += map.get(nums[i]);
            stack.add(i);
        }
        return cnt;
    }
}
