package linshen.structure;

/*
给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。

你可以对数组执行 至多 k 次操作：

从数组中选择一个下标 i ，将 nums[i] 增加 或者 减少 1 。
最终数组的频率分数定义为数组中众数的 频率 。

请你返回你可以得到的 最大 频率分数。

众数指的是数组中出现次数最多的数。一个元素的频率指的是数组中这个元素的出现次数。

 */

import java.util.Arrays;

public class L2968 {
    public int maxFrequencyScore(int[] nums, long k) {
        Arrays.sort(nums);
        int n = nums.length;
        long[] sum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        int max = 1;
        for (int i = 0; i < n; i++) {
            int x = find(nums, sum, 0, i, i, k);
            if (x != -1) {
                max = Math.max(max, i - x + 1);
            }
        }
        return max;
    }

    //find first element that satisfies can
    private int find(int[] nums, long[] sum, int left, int right, int end, long k) {
        if (left == right) {
            if (can(nums, sum, left, end, k)) return left;
            else return -1;
        } else if (left == right - 1) {
            if (can(nums, sum, left, end, k)) return left;
            else if (can(nums, sum, right, end, k)) return right;
            else return -1;
        } else {
            int mid = (left + right) / 2;
            if (can(nums, sum, mid, end, k)) {
                return find(nums, sum, left, mid, end, k);
            } else {
                return find(nums, sum, mid + 1, right, end, k);
            }
        }
    }

    //todo h 记住中位数贪心，优化这个函数
    private boolean can(int[] nums, long[] sum, int left, int right, long k) {
        for (int i = left; i <= right; i++) {
            long t = (i - left) * (long) (nums[i]) - (sum[i] - sum[left]) + (sum[right + 1] - sum[i + 1] - (right - i) * (long) (nums[i]));
            if (t <= k) return true;
        }
        return false;
    }


}
