package linshen.binary;

/*
给你一个下标从 0 开始的整数数组 nums 。

一开始，所有下标都没有被标记。你可以执行以下操作任意次：

选择两个 互不相同且未标记 的下标 i 和 j ，满足 2 * nums[i] <= nums[j] ，标记下标 i 和 j 。
请你执行上述操作任意次，返回 nums 中最多可以标记的下标数目。
2 4 5 9

1 <= nums.length <= 105
1 <= nums[i] <= 109
 */

import java.util.Arrays;

public class L2576 {
    public int maxNumOfMarkedIndices(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return 2*findMax(nums, 0, n / 2);
    }

    private int findMax(int[] nums, int left, int right) {
        if(left == right)return right;
        else if(left == right - 1) {
            if(check(nums, right))return right;
            else return left;
        }else {
            int mid = (left + right) / 2;
            if(check(nums, mid))return findMax(nums, mid, right);
            else return findMax(nums, left, mid - 1);
        }
    }

    private boolean check(int[] nums, int pair) {
        for (int i = 0; i < pair; i++){
            int t = nums.length - pair + i;
            if(nums[t] < 2 * nums[i])return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new L2576().maxNumOfMarkedIndices(new int[]{2,4,5,9}));
    }
}
