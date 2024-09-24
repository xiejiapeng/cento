package index.prefix;

/*
给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。

题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。

请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
 */

import java.util.Arrays;

public class L238 {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        Arrays.fill(left, 1);
        for (int i = 1; i < n; i++){
            left[i] = left[i-1] * nums[i-1];
        }

        int[] right = new int[n];
        Arrays.fill(right, 1);
        for (int i = n - 1; i > -1; i--){
            right[i] = right[i-1] * nums[i-1];
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++){
            ans[i] = left[i] * right[i];
        }
        return ans;
    }
}
