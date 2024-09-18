package index.bfs;

/*
给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的
子集
（幂集）。

解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 */

import java.util.*;

public class L90 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> ans = new HashSet<>();
        dfs(nums, 0, new LinkedList<>(), ans);
        return new ArrayList<>(ans);
    }

    private void dfs(int[] nums, int idx, LinkedList<Integer> cur, Set<List<Integer>> ans) {
        if (idx == nums.length) {
            ans.add(new ArrayList<>(cur));
        } else {
            //add
            cur.addLast(nums[idx]);
            dfs(nums, idx + 1, cur, ans);
            cur.removeLast();

            //skip
            dfs(nums, idx + 1, cur, ans);
        }
    }


}
