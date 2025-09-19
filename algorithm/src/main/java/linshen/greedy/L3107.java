package linshen.greedy;

/*
给你一个整数数组 nums 和一个 非负 整数 k 。一次操作中，你可以选择任一元素 加 1 或者减 1 。

请你返回将 nums 中位数 变为 k 所需要的 最少 操作次数。

一个数组的中位数指的是数组按非递减顺序排序后最中间的元素。如果数组长度为偶数，
我们选择中间两个数的较大值为中位数。
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class L3107 {
    //todo hhh 中位数问题常引入两个队列
    public long minOperationsToMakeMedianK(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        PriorityQueue<Integer> left = new PriorityQueue<>(Comparator.comparingInt(o -> -o));
        PriorityQueue<Integer> right = new PriorityQueue<>();
        int mid = n / 2;
        long c = 0;
        for (int i = 0; i < n; i++){
            if(i < mid)left.add(nums[i]);
            else right.add(nums[i]);
        }
        while (right.peek() != k) {
            int x = right.poll();
            if(x > k) {
                int floor = k;
                c += (x - floor);
                x = floor;
                if(!left.isEmpty() && x < left.peek()) {
                    int y = left.poll();
                    right.add(y);
                    left.add(floor);
                } else {
                    right.add(x);
                }
            } else {
                int celling = k;
                c += (celling - x);
                x = celling;
                right.add(x);
            }
        }
        return c;
    }

    public static void main(String[] args) {
        System.out.println(new L3107().minOperationsToMakeMedianK(new int[]{1,1,1}, 1000000000));
    }
}
