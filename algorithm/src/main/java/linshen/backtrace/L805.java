package linshen.backtrace;

/*
给定你一个整数数组 nums

我们要将 nums 数组中的每个元素移动到 A 数组 或者 B 数组中，使得 A 数组和 B 数组不为空，
并且 average(A) == average(B) 。

如果可以完成则返回true ， 否则返回 false 。

注意：对于数组 arr , average(arr) 是 arr 的所有元素的和除以 arr 长度。
<X>:<Y>
<x>+<y>

提示:

1 <= nums.length <= 30
0 <= nums[i] <= 104
 */

import java.util.*;

import java.util.*;

public class L805 {
    /*
    todo hhhhh
    1. 回顾提交过的一次超时的做法，其是dfs的标准做法，但这里会超时
    2. meet in middle做法是很强大的剪枝技巧，将集合分成左右两部分，再分别累计统计
    3. 注意能剪枝就提前剪枝
     */
    public boolean splitArraySameAverage(int[] nums) {
        int cnt = nums.length;
        int totalSum = Arrays.stream(nums).sum();

        // ------------------ 数学剪枝 1：检查是否存在可行的子集大小 ------------------
        boolean possible = false;
        for (int k = 1; k < cnt; k++) {
            if ((totalSum * k) % cnt == 0) {
                possible = true;
                break;
            }
        }
        if (!possible) return false; // 没有可行的子集，直接返回

        // ------------------ 构造左右子集和 ------------------
        Map<Integer, Set<Integer>> ml = construct(nums, 0, cnt / 2);
        Map<Integer, Set<Integer>> mr = construct(nums, cnt / 2, cnt); // 注意右半起点

        for (int x : ml.keySet()) {
            for (int y : mr.keySet()) {
                if (x + y == 0 || x + y == cnt) continue; // ------------------ 剪枝 2：避免空子集 ------------------
                Set<Integer> sl = ml.get(x);
                Set<Integer> sr = mr.get(y);
                for (int a : sl) {
                    for (int b : sr) {
                        int s = a + b;
                        int c = x + y;
                        if (s * cnt == totalSum * c) {
                            return true; // 满足平均数相等
                        }
                    }
                }
            }
        }
        return false;
    }

    private Map<Integer, Set<Integer>> construct(int[] nums, int left, int right) {
        Map<Integer, Set<Integer>> ans = new HashMap<>();
        dfs(nums, left, right, left, 0, 0, ans);
        return ans;
    }

    private void dfs(int[] nums, int left, int right, int cur, int cnt, int sum, Map<Integer, Set<Integer>> ans) {
        if (cur == right) {
            // ------------------ 剪枝 3：子集和加入 map ------------------
            ans.putIfAbsent(cnt, new HashSet<>());
            ans.get(cnt).add(sum);
        } else {
            // ------------------ 剪枝 4：子集大小限制 ------------------
            if (cnt + 1 <= right - left) { // 当前子集大小不超过当前区间长度
                dfs(nums, left, right, cur + 1, cnt + 1, sum + nums[cur], ans); // 选当前元素
            }

            // ------------------ 剪枝 5：空子集允许不选元素继续递归 ------------------
            dfs(nums, left, right, cur + 1, cnt, sum, ans); // 不选当前元素
        }
    }

    public static void main(String[] args) {
        int[] nums = {3,1};
        System.out.println(new L805().splitArraySameAverage(nums));
    }
}

