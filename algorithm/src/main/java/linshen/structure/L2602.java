package linshen.structure;

/*
给你一个正整数数组 nums 。

同时给你一个长度为 m 的整数数组 queries 。第 i 个查询中，你需要将 nums 中所有元素变成 queries[i] 。你可以执行以下操作 任意 次：

将数组里一个元素 增大 或者 减小 1 。
请你返回一个长度为 m 的数组 answer ，其中 answer[i]是将 nums 中所有元素变成 queries[i] 的 最少 操作次数。

注意，每次查询后，数组变回最开始的值。
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L2602 {
    public static void main(String[] args) {
        System.out.println(new L2602().minOperations(new int[]{2, 9, 6, 3}, new int[]{10}));
    }

    public List<Long> minOperations(int[] nums, int[] queries) {
        int n = nums.length;
        Arrays.sort(nums);
        long[] sum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        List<Long> ans = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            int t = find(nums, queries[i], 0, n - 1);
            if (t == -1) {
                ans.add(sum[n] - n * (long) queries[i]);
            } else {
                ans.add((t + 1) * (long) queries[i] - sum[t + 1] + (sum[n] - sum[t + 1]) - (n - t - 1) * (long) queries[i]);
            }
        }
        return ans;
    }

    //find the last element that less or equal than x, otherwise -1
    private int find(int[] nums, int x, int left, int right) {
        if (left == right) {
            if (nums[right] <= x) return right;
            else return -1;
        } else if (left == right - 1) {
            if (nums[right] <= x) return right;
            else if (nums[left] <= x) return left;
            else return -1;
        } else {
            int mid = (left + right) / 2;
            if (nums[mid] <= x) return find(nums, x, mid, right);
            else return find(nums, x, left, mid - 1);
        }
    }
}
