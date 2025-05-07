package linshen.dp;

/*
给定一个长度为 n 的环形整数数组 nums ，返回 nums 的非空 子数组 的最大可能和 。

环形数组 意味着数组的末端将会与开头相连呈环状。形式上，
nums[i] 的下一个元素是 nums[(i + 1) % n] ， nums[i] 的前一个元素是 nums[(i - 1 + n) % n] 。

子数组 最多只能包含固定缓冲区 nums 中的每个元素一次。形式上，
对于子数组 nums[i], nums[i + 1], ..., nums[j] ，不存在 i <= k1, k2 <= j 其中 k1 % n == k2 % n 。

 提示：

n == nums.length
1 <= n <= 3 * 104
-3 * 104 <= nums[i] <= 3 * 104

5 -3 5 5 -3 5
 */

import java.util.PriorityQueue;

public class L918 {
    public int maxSubarraySumCircular(int[] arr) {
        int n = arr.length;
        int[] nums = new int[2 * n];
        for (int i = 0; i < n; i++){
            nums[i] = arr[i];
            nums[i + n] = arr[i];
        }
        int[] s = new int[2*n+1];
        for (int i = 1; i <= 2 * n; i++){
            s[i] = s[i-1] + nums[i-1];
        }
        int ans = Integer.MIN_VALUE;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(0);
        for (int left = 0, right = 1; right <= 2 * n; right++){
            while (right - left > n) {
                queue.remove(s[left]);
                left++;
            }
            ans = Math.max(ans, s[right] - queue.peek());
            queue.add(s[right]);
        }
        return ans;

    }

    public static void main(String[] args) {
        System.out.println(new L918().maxSubarraySumCircular(new int[]{5,-3,5}));
    }
}
