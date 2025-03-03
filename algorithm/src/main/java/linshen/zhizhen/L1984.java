package linshen.zhizhen;

/*
给你一个 下标从 0 开始 的整数数组 nums ，其中 nums[i] 表示第 i 名学生的分数。另给你一个整数 k 。

从数组中选出任意 k 名学生的分数，使这 k 个分数间 最高分 和 最低分 的 差值 达到 最小化 。

返回可能的 最小差值 。


 */

import java.util.Arrays;

public class L1984 {
    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        for (int left = 0; left + k - 1 < n; left++) {
            int right = left + k - 1;
            min = Math.min(min, nums[right]-nums[left]);
        }
        return min;
    }
}
