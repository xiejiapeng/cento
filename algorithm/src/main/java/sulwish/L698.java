package sulwish;

/*
给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 */

import java.util.*;

public class L698 {
    Set<List<Integer>> groups = new HashSet<>();
    boolean ans = false;
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        if(sum % k != 0)return false;
        int target = sum / k;
        dfs(nums, new LinkedList<>(), 0, target, 0);
        System.out.println(groups);


        List<List<Integer>> gs = new ArrayList<>(groups);
        visit(n, gs, 0, new LinkedList<>());
        return ans;
    }

    private void dfs(int[] nums, LinkedList<Integer> cur, int idx, int target ,int s) {
        if(idx == nums.length) {
            if(s == target) {
                groups.add(new ArrayList<>(cur));
            }
        } else {
            //add
            if(s < target) {
                cur.addLast(idx);
                dfs(nums, cur, idx + 1, target, s + nums[idx]);
                cur.removeLast();
            }
            dfs(nums, cur, idx + 1, target, s);
        }
    }

    private void visit(int n, List<List<Integer>> gs, int idx, LinkedList<Integer> cur) {
        if(idx == gs.size()) {
            if(cur.size() == n) {
                ans = true;
            }
        } else {
            List<Integer> c = gs.get(idx);
            //can add
            if(camp(cur, c)) {
                for (int x : c) {
                    cur.addLast(x);
                }
                visit(n, gs, idx+1, cur);
                for (int x : c) {
                    cur.removeLast();
                }
            }
            //don't add
            visit(n, gs, idx+1, cur);

        }
    }

    private boolean camp(List<Integer> a, List<Integer> b) {
        for (int x : a) {
            if(b.contains(x))return false;
        }
        for (int y : b) {
            if(a.contains(y))return false;
        }
        return true;
    }
}
