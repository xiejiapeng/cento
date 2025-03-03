package linshen.zhizhen;

/*
给你一个下标从 0 开始的数组 nums ，数组中有 n 个整数，另给你一个整数 k 。

半径为 k 的子数组平均值 是指：nums 中一个以下标 i 为 中心 且 半径 为 k 的子数组中所有元素的平均值，即下标在 i - k 和 i + k 范围（含 i - k 和 i + k）内所有元素的平均值。如果在下标 i 前或后不足 k 个元素，那么 半径为 k 的子数组平均值 是 -1 。

构建并返回一个长度为 n 的数组 avgs ，其中 avgs[i] 是以下标 i 为中心的子数组的 半径为 k 的子数组平均值 。

x 个元素的 平均值 是 x 个元素相加之和除以 x ，此时使用截断式 整数除法 ，即需要去掉结果的小数部分。

例如，四个元素 2、3、1 和 5 的平均值是 (2 + 3 + 1 + 5) / 4 = 11 / 4 = 2.75，截断后得到 2 。
 */

import java.util.Arrays;

public class L2090 {
    public int[] getAverages(int[] nums, int k) {
        int len = nums.length;
        int[] ans = new int[len];
        //注意要用long
        long sum = 0;
        //todo right的判断条件为len + k
        for (int right = 0; right - k < len; right++) {
            int left = right - 2 * k;
            int i = right - k;
            //左右边界都可能超
            if(right < len)sum += nums[right];
            if(left - 1 >= 0)sum -= nums[left-1];
            //同时判断左右
            if(left >= 0 && right < len) {
                ans[i] = (int)(sum / (2 * k + 1));
            } else {
                if(i >= 0)ans[i] = -1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new L2090().getAverages(new int[]{1,2,3,4,5}, 2)));
    }
}
