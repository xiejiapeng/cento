package index.dfs;

import java.util.*;

/*
给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以
使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用 一次 。

注意：解集不能包含重复的组合。
 */

public class L40 {
    public static void main(String[] args) {
        System.out.println(new L40().combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 2));
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < candidates.length; i++) {
            count.put(candidates[i], count.getOrDefault(candidates[i], 0) + 1);
        }
        Set<List<Integer>> ans = new HashSet<>();
        dfs(candidates, target, 0, 0, new LinkedList<>(), ans, count);
        return new ArrayList<>(ans);
    }

    private void dfs(int[] candidates, int target, int id, int sum, LinkedList<Integer> l, Set<List<Integer>> ans, Map<Integer, Integer> count) {
        if (id == candidates.length) {
            if (target == sum) {
                ans.add(new ArrayList<>(l));
            }
        } else {
            if (id - 1 >= 0 && candidates[id] == candidates[id - 1]) {
                dfs(candidates, target, id + 1, sum, l, ans, count);
            } else {
                dfs(candidates, target, id + 1, sum, l, ans, count);
                int c = Math.min(count.get(candidates[id]), (target - sum) / candidates[id]);
                for (int i = 1; i <= c; i++) {
                    l.addLast(candidates[id]);
                    dfs(candidates, target, id + 1, sum + i * candidates[id], l, ans, count);

                }
                for (int i = 1; i <= c; i++) {
                    l.removeLast();
                }
            }


        }
    }
}
