package index.bfs;

import sulqn.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class L1161 {
    public int maxLevelSum(TreeNode root) {
        Map<Integer, Integer> sum = new HashMap<>();
        dfs(root, sum, 0);
        int max = Integer.MIN_VALUE;
        int id = -1;
        System.out.println(sum);
        for (int idx : sum.keySet()) {
            int s = sum.get(idx);
            if (s > max) {
                max = s;
                id = idx;
            } else if (s == max) {
                id = Math.min(id, idx);
            }
        }
        return id;
    }

    void dfs(TreeNode root, Map<Integer, Integer> sum, int height) {
        sum.put(height, sum.getOrDefault(height, 0) + root.val);
        if (root.left != null) {
            dfs(root.left, sum, height + 1);
        }
        if (root.right != null) {
            dfs(root.right, sum, height + 1);
        }

    }
}
