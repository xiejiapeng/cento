package linshen.structure.heap;

/*
给你一个下标从 0 开始的整数数组 nums ，它包含 3 * n 个元素。

你可以从 nums 中删除 恰好 n 个元素，剩下的 2 * n 个元素将会被分成两个 相同大小 的部分。

前面 n 个元素属于第一部分，它们的和记为 sumfirst 。
后面 n 个元素属于第二部分，它们的和记为 sumsecond 。
两部分和的 差值 记为 sumfirst - sumsecond 。

比方说，sumfirst = 3 且 sumsecond = 2 ，它们的差值为 1 。
再比方，sumfirst = 2 且 sumsecond = 3 ，它们的差值为 -1 。
请你返回删除 n 个元素之后，剩下两部分和的 差值的最小值 是多少。
2  7 100 1 3 3
 */

import java.util.PriorityQueue;

public class L2163 {
    public static void main(String[] args) {
        System.out.println(new L2163().minimumDifference(new int[]{24, 32, 19, 29, 8, 44, 5, 38, 26, 42, 19, 20, 31, 48, 32, 37, 17, 28, 47, 7, 29, 46, 41, 1, 17, 24, 48, 12, 48, 10, 26, 44, 50, 16, 32, 46, 28, 37, 44}));
    }

    public long minimumDifference(int[] nums) {
        //todo hhhhh 未能解决的一题
        int m = nums.length;
        int n = m / 3;
        PriorityQueue<Integer> first = new PriorityQueue<>((a, b) -> (b - a));
        long min = Long.MAX_VALUE;
        long fs = 0;
        PriorityQueue<Integer> second = new PriorityQueue<>();
        long[] rs = new long[m];
        long r = 0;
        for (int i = m - 1; i > -1; i--) {
            if (second.size() < n) {
                second.add(nums[i]);
                r += nums[i];
            } else {
                if (nums[i] > second.peek()) {
                    r -= second.poll();
                    second.add(nums[i]);
                    r += nums[i];
                }
            }
            rs[i] = r;
        }
        for (int i = 0; i < 2 * n; i++) {
            //尽量不要将if条件嵌套在一起，尽量用多个if else分隔
            if (first.size() < n) {
                first.add(nums[i]);
                fs += nums[i];
            } else {
                if (nums[i] < first.peek()) {
                    fs -= first.poll();
                    first.add(nums[i]);
                    fs += nums[i];
                }
            }

            if (i >= n - 1) {
                min = Math.min(min, fs - rs[i + 1]);
                if (min == -231) {
                    System.out.println("i = " + i + ", fs = " + fs + ", r = " + rs[i + 1]);
                }
            }

        }

        return min;
    }
}
