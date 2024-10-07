package index.monoqueue;

/*
给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。

如果不存在满足条件的子数组，则返回 0 。

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
0 <= limit <= 10^9
 */

import java.util.LinkedList;

public class L1438 {
    public int longestSubarray(int[] nums, int limit) {
        int n = nums.length;
        return find(nums, 1, n, limit);
    }

    private int find(int[] nums, int left, int right, int limit) {
        if (left == right) {
            if (can(nums, limit, left)) return left;
            else return -1;
        } else if (left == right - 1) {
            if (can(nums, limit, right)) return right;
            else if (can(nums, limit, left)) return left;
            else return -1;
        } else {
            int mid = (left + right) / 2;
            if (can(nums, limit, mid)) {
                return find(nums, mid, right, limit);
            } else {
                return find(nums, left, mid - 1, limit);
            }
        }
    }

    private boolean can(int[] nums, int limit, int len) {
        int n = nums.length;
        LinkedList<Integer> s1 = new LinkedList<>();
        LinkedList<Integer> s2 = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            //get max
            while (!s1.isEmpty() && nums[s1.getLast()] <= nums[i]) {
                s1.removeLast();
            }
            //get min
            while (!s2.isEmpty() && nums[s2.getLast()] >= nums[i]) {
                s2.removeLast();
            }
            s1.add(i);
            s2.add(i);
            int toRemove = i - len;
            if (!s1.isEmpty() && s1.getFirst() == toRemove) {
                s1.removeFirst();
            }
            if (!s2.isEmpty() && s2.getFirst() == toRemove) {
                s2.removeFirst();
            }
            int max = s1.isEmpty() ? nums[i] : nums[s1.getFirst()];
            int min = s2.isEmpty() ? nums[i] : nums[s2.getFirst()];
            if (i >= len - 1 && Math.abs(max - min) <= limit) {
                System.out.println(len + " can, end at " + i);
                return true;
            }
        }
        System.out.println(len + " can't");
        return false;
    }
}
