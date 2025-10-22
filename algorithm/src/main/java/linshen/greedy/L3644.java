package linshen.greedy;

/*
给你一个长度为 n 的整数数组 nums，其中 nums 是范围 [0..n - 1] 内所有数字的一个 排列 。

你可以在满足条件 nums[i] AND nums[j] == k 的情况下交换下标 i 和 j 的元素，
其中 AND 表示按位与操作，k 是一个非负整数。
0 0 0 1

返回可以使数组按 非递减 顺序排序的最大值 k（允许进行任意次这样的交换）。如果 nums 已经是有序的，返回 0。

排列 是数组所有元素的一种重新排列。
0 1 2 3
0 3 2 1
11 - 01
 */

public class L3644 {
    //todo hh 如果某个元素不在其位置，必定会将k的某些位置为0，这是k的必要条件；充分条件我没有证明；很多贪心都是这种特点吗？得到必要性，然后猜出答案
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        int t = 1;
        while (Math.pow(2, t) - 1 < n - 1) {
            t++;
        }
        boolean find = false;
        int u = (int)Math.pow(2, t) - 1;
        for (int i = 0; i < nums.length; i++){
            if(nums[i] != i) {
                find = true;
                u &= nums[i];
            }
        }
        if(!find)u = 0;
        return u;
    }
}
