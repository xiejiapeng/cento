package sulwish;

import java.util.*;
import java.util.stream.Collectors;

/*
给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用 一次 。

注意：解集不能包含重复的组合。
 */

public class L40 {
    Set<List<Integer>> ans = new HashSet<>();

    public static void main(String[] args) {
        int[] arr = new int[]{14, 6, 25, 9, 30, 20, 33, 34, 28, 30, 16, 12, 31, 9, 9, 12, 34, 16, 25, 32, 8, 7, 30, 12, 33, 20, 21, 29, 24, 17, 27, 34, 11, 17, 30, 6, 32, 21, 27, 17, 16, 8, 24, 12, 12, 28, 11, 33, 10, 32, 22, 13, 34, 18, 12};
        System.out.println(new L40().combinationSum2(arr, 27));
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : candidates) {
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
        }
        Set<Integer> all = Arrays.stream(candidates).boxed().collect(Collectors.toSet());
        int[] c2 = new int[all.size()];
        int i = 0;
        for (int x : all) {
            c2[i++] = x;
        }
        dfs(c2, cnt, 0, new LinkedList<>(), 0, target);
        return new ArrayList<>(ans);
    }

    private void dfs(int[] candidates, Map<Integer, Integer> cnt, int cur, LinkedList<Integer> l, int sum, int target) {
        if (sum > target) return;
        if (cur == candidates.length) {
            if (sum == target) {
                ans.add(new ArrayList<>(l));
            }
            return;
        } else {
            dfs(candidates, cnt, cur + 1, l, sum, target);

            int cn = cnt.get(candidates[cur]);
            for (int i = 1; i <= cn; i++) {
                l.addLast(candidates[cur]);
                dfs(candidates, cnt, cur + 1, l, sum + i * candidates[cur], target);
            }
            for (int i = 1; i <= cn; i++) {
                l.removeLast();
            }
        }
    }
}
