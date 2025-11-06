package linshen.backtrace;

/*
给你一个整数数组 nums，其中包含的正整数 互不相同 ，另给你一个整数 target。

请判断是否可以将 nums 分成两个 非空、互不相交 的 子集 ，并且每个元素必须 恰好 属于 一个 子集，
使得这两个子集中元素的乘积都等于 target。

如果存在这样的划分，返回 true；否则，返回 false。

子集 是数组中元素的一个选择集合。
提示：

3 <= nums.length <= 12
1 <= target <= 1015
1 <= nums[i] <= 100
nums 中的所有元素互不相同。
 */

import java.util.*;

public class L3566 {
    public boolean checkEqualPartitions(int[] nums, long target) {
        Set<Integer> l = new HashSet<>();
        return dfs(nums, 0, l, target);
    }

    private boolean dfs(int[] nums, int cur, Set<Integer> l, long target) {
        if(cur == nums.length) {
            Set<Integer> lx = new HashSet<>();
            for (int x : nums) {
                if(!l.contains(x))lx.add(x);
            }
            return can(target, l) && can(target, lx);
        } else {
            boolean ans = false;
            //choose first
            l.add(nums[cur]);
            ans |= dfs(nums, cur + 1, l, target);
            l.remove(nums[cur]);

            //skip first
            ans |= dfs(nums, cur + 1, l, target);
            return ans;
        }
    }

    private boolean can(long target, Set<Integer> l) {
        if(l.isEmpty())return false;
        else {
            for (int x : l) {
                if(target < x)return false;
                if(target % x != 0)return false;
                target /= x;
            }
            return target == 1;
        }
    }
}
