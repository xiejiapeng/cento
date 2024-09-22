package index.fenkuai;

/*
给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

返回 滑动窗口中的最大值 。

1 <= nums.length <= 105
-104 <= nums[i] <= 104
1 <= k <= nums.length
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class L239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            int x = nums[i];
            while (!queue.isEmpty() && nums[queue.getLast()] <= x) {
                queue.removeLast();
            }
            queue.addLast(i);
        }
        List<Integer> ans = new ArrayList<>();
        ans.add(nums[queue.getFirst()]);
        for (int i = k; i < n; i++) {
            int s = i - k;
            if (!queue.isEmpty() && queue.getFirst() == s) {
                queue.removeFirst();
            }
            while (!queue.isEmpty() && nums[queue.getLast()] <= nums[i]) {
                queue.removeLast();
            }
            queue.addLast(i);
            ans.add(nums[queue.getFirst()]);
        }
        int[] aa = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            aa[i] = ans.get(i);
        }
        return aa;
    }
}
