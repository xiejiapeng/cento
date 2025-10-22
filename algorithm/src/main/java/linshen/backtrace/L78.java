package linshen.backtrace;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
提示：

1 <= nums.length <= 10
-10 <= nums[i] <= 10
nums 中的所有元素 互不相同
 */

public class L78 {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        LinkedList<Integer> l = new LinkedList<>();
        dfs(nums, 0, l);
        return ans;
    }

    private void dfs(int[] nums, int cur, LinkedList<Integer> l) {
        if(cur == nums.length) {
            ans.add(new ArrayList<>(l));
        } else {
            //choose
            l.addLast(nums[cur]);
            dfs(nums, cur + 1, l);
            l.removeLast();

            //don't choose
            dfs(nums, cur + 1, l);
        }
    }
}
