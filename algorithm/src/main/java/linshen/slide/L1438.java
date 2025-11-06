package linshen.slide;

/*
给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，
该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。
如果不存在满足条件的子数组，则返回 0 。

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
0 <= limit <= 10^9
 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class L1438 {
    public int longestSubarray(int[] nums, int limit) {
        int n = nums.length;
        PriorityQueue<int[]> min = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        PriorityQueue<int[]> max = new PriorityQueue<>(Comparator.comparing(a -> -a[1]));
        int ans = Integer.MIN_VALUE;
        for (int left = 0, i = 0; i < n; i++){
            while (!min.isEmpty() && min.peek()[1] < nums[i] - limit) {
                int[] tmp = min.poll();
                //todo h 记得取max
                left = Math.max(left,tmp[0] + 1);
            }
            while (!max.isEmpty() && max.peek()[1] > nums[i] + limit) {
                int[] tmp = max.poll();
                left = Math.max(left,tmp[0] + 1);
            }
            min.add(new int[]{i, nums[i]});
            max.add(new int[]{i, nums[i]});
            ans = Math.max(ans, i - left + 1);
        }
        return ans == Integer.MIN_VALUE ? 0 : ans;
    }

    public static void main(String[] args) {
        System.out.println(new L1438().longestSubarray(new int[]{2,2,2,4,4,2,5,5,5,5,5,2}, 2));
    }
}
