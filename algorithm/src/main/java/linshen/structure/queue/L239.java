package linshen.structure.queue;

/*
给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
返回 滑动窗口中的最大值 。
示例 1：
输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
输出：[3,3,5,5,6,7]
解释：
滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class L239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        LinkedList<Integer> queue = new LinkedList<>();
        int[] ans = new int[n-k+1];
        for (int i = 0; i < n; i++) {
            while (!queue.isEmpty() && nums[queue.getLast()] <= nums[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
            int tail = i - k;
            if(i > k - 1 && queue.getFirst() == tail) {
                queue.pollFirst();
            }
            if(i >= k-1) {
                ans[i-k+1] = nums[queue.getFirst()];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new L239().maxSlidingWindow(new int[]{1,3,-1,-3}, 3)));
    }
}
