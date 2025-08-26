package linshen.greedy;

/*
给你一个长度为 n 的 正 整数数组 nums 。

多边形 指的是一个至少有 3 条边的封闭二维图形。多边形的 最长边 一定 小于 所有其他边长度之和。

如果你有 k （k >= 3）个 正 数 a1，a2，a3, ...，ak 满足 a1 <= a2 <= a3 <= ... <= ak
且 a1 + a2 + a3 + ... + ak-1 > ak ，那么 一定 存在一个 k 条边的多边形，
每条边的长度分别为 a1 ，a2 ，a3 ， ...，ak 。

一个多边形的 周长 指的是它所有边之和。

请你返回从 nums 中可以构造的 多边形 的 最大周长 。如果不能构造出任何多边形，请你返回 -1 。
 */

import java.util.Arrays;

public class L2971 {
    public long largestPerimeter(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        long max = -1;
        long s = 0;
        for (int i = 0; i < n; i++){
            if(nums[i] < s && i + 1 >= 3) {
                max = Math.max(max, s + nums[i]);
            }
            s += nums[i];
        }
        return max;
    }
}
