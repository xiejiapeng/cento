package index.dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class L39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(candidates, target, 0, 0, new LinkedList<>(), ans);
        return ans;
    }

    private void dfs(int[] candidates, int target, int id, int sum, LinkedList<Integer> l, List<List<Integer>> ans) {
        if (id == candidates.length) {
            if (sum == target) {
                ans.add(new ArrayList<>(l));
            }
        } else {
            dfs(candidates, target, id + 1, sum, l, ans);
            int remain = target - sum;
            int count = remain / candidates[id];

            for (int i = 1; i <= count; i++) {
                l.addLast(candidates[id]);
                dfs(candidates, target, id + 1, sum + i * candidates[id], l, ans);
            }
            for (int i = 1; i <= count; i++) {
                l.removeLast();
            }
        }
    }
}
