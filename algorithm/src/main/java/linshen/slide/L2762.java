package linshen.zhizhen;

/*
给你一个下标从 0 开始的整数数组 nums 。nums 的一个子数组如果满足以下条件，那么它是 不间断 的：

i，i + 1 ，...，j  表示子数组中的下标。对于所有满足 i <= i1, i2 <= j 的下标对，都有 0 <= |nums[i1] - nums[i2]| <= 2 。
请你返回 不间断 子数组的总数目。

子数组是一个数组中一段连续 非空 的元素序列。
 */

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class L2762 {
    public long continuousSubarrays(int[] nums) {
        int n = nums.length;
        int[][] ns = new int[n][2];
        for (int i = 0; i < nums.length; i++){
            ns[i] = new int[]{nums[i], i};
        }

        PriorityQueue<int[]> min = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        PriorityQueue<int[]> max = new PriorityQueue<>(Comparator.comparingInt(a -> -a[0]));

        //todo 记住类型！！！
        long ans = 0;
        for (int left = 0, right = 0; right < n; right++){
            int mid = -1;
            min.add(ns[right]);
            max.add(ns[right]);
            while (min.peek()[0] < nums[right] - 2) {
                int[] tmp = min.poll();
                mid = Math.max(mid, tmp[1]);
            }
            while (max.peek()[0] > nums[right] + 2) {
                int[] tmp = max.poll();
                mid = Math.max(mid, tmp[1]);
            }
            if(mid != -1) {
                left = mid + 1;
            }
            ans += (right - left + 1);
        }
        return ans;
    }
}
