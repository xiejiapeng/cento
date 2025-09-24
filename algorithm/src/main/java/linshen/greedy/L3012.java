package linshen.greedy;

import java.util.Arrays;
import java.util.TreeMap;

/*
给你一个下标从 0 开始的整数数组 nums ，它只包含 正 整数。

你的任务是通过进行以下操作 任意次 （可以是 0 次） 最小化 nums 的长度：

在 nums 中选择 两个不同 的下标 i 和 j ，满足 nums[i] > 0 且 nums[j] > 0 。
将结果 nums[i] % nums[j] 插入 nums 的结尾。
将 nums 中下标为 i 和 j 的元素删除。
请你返回一个整数，它表示进行任意次操作以后 nums 的 最小长度 。
3 8 10
[1,1,3,4]
[2,2,2,5,9,10]
-> [1,2,2,9,10]
-> [1,1,2,10]
-> [1,1]
-> [1]
 */
public class L3012 {
    //todo hhh 想到了要找最小数，但是怎么求最小数？？要盯着当前的最小数，如果能得到哪怕“一个”比它小的都足够！！
    //只要有一个即可！！！
    public int minimumArrayLength(int[] nums) {
        int m = Integer.MAX_VALUE;
        for (int x : nums) {
            m = Math.min(m, x);
        }

        for (int x : nums) {
            if (x % m > 0) {
                return 1;
            }
        }

        int cnt = 0;
        for (int x : nums) {
            if (x == m) {
                cnt++;
            }
        }
        return (cnt + 1) / 2;
    }

}
