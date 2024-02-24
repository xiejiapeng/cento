package sulwish;

import java.util.*;

/*
给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。

candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。

对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 */
public class L39 {
    Set<List<Integer>> ans = new HashSet<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates, 0, 0, target, new LinkedList<>());
        return new ArrayList<>(ans);
    }

    private void dfs(int[] candidates, int cur, int curSum, int target, LinkedList<Integer> l) {
        if (cur == candidates.length) {
            if (curSum == target) {
                List<Integer> x = new ArrayList<>(l);
                ans.add(x);
            }
            return;
        }

        if (candidates[cur] + curSum <= target) {
            //choose cur
            l.addLast(candidates[cur]);
            dfs(candidates, cur, curSum + candidates[cur], target, l);
            l.removeLast();

            //don't choose cur
            dfs(candidates, cur + 1, curSum, target, l);
        } else {
            dfs(candidates, cur + 1, curSum, target, l);
        }
    }
}
