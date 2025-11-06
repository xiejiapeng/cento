package linshen.structure;

/*
给你一个 非递减 有序整数数组 nums 。

请你建立并返回一个整数数组 result，它跟 nums 长度相同，且result[i] 等于 nums[i] 与数组中所有其他元素差的绝对值之和。

换句话说， result[i] 等于 sum(|nums[i]-nums[j]|) ，其中 0 <= j < nums.length 且 j != i （下标从 0 开始）。
提示：

2 <= nums.length <= 105
1 <= nums[i] <= nums[i + 1] <= 104
 */

import java.util.Arrays;
import java.util.Comparator;

public class L1685 {
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;
        int[][] ns = new int[n][2];
        for (int i = 0; i < n; i++) {
            ns[i] = new int[]{i, nums[i]};
        }
        Arrays.sort(ns, Comparator.comparingInt(o -> o[1]));
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + ns[i - 1][1];
        }

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[ns[i][0]] = i * ns[i][1] - sum[i] + (sum[n] - sum[i + 1]) - (n - i - 1) * ns[i][1];
        }
        return result;
    }
}
