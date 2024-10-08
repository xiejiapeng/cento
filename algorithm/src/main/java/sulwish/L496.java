package sulwish;

/*
nums1 中数字 x 的 下一个更大元素 是指 x 在 nums2 中对应位置 右侧 的 第一个 比 x 大的元素。

给你两个 没有重复元素 的数组 nums1 和 nums2 ，下标从 0 开始计数，其中nums1 是 nums2 的子集。

对于每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2 确定 nums2[j] 的 下一个更大元素 。如果不存在下一个更大元素，那么本次查询的答案是 -1 。

返回一个长度为 nums1.length 的数组 ans 作为答案，满足 ans[i] 是如上所述的 下一个更大元素 。
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class L496 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n = nums2.length;
        int[] right = new int[n];

        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> id = new HashMap<>();
        for (int i = n - 1; i > -1; i--) {
            id.put(nums2[i], i);
            if (stack.isEmpty()) {
                stack.add(i);
                right[i] = -1;
            } else {
                while (!stack.isEmpty() && nums2[stack.peek()] <= nums2[i]) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    right[i] = -1;
                } else {
                    right[i] = stack.peek();
                }

                stack.add(i);
            }
        }

        System.out.println(Arrays.toString(right));
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            int t = id.get(nums1[i]);
            int a = right[t];
            ans[i] = a == -1 ? -1 : nums2[a];
        }
        return ans;
    }
}
