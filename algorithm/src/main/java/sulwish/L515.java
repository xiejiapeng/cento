package sulwish;

import sulqn.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

/*
给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 */

public class L515 {
    Map<Integer, Integer> max = new HashMap<>();

    public List<Integer> largestValues(TreeNode root) {
        if (root == null) return new ArrayList<>();
        dfs(root, 0);
        return max.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getKey))
                .map(e -> e.getValue()).collect(Collectors.toList());
    }

    private void dfs(TreeNode cur, int level) {
        if (max.containsKey(level)) {
            int large = max.get(level);
            max.put(level, Math.max(cur.val, large));
        } else {
            max.put(level, cur.val);
        }

        if (cur.left != null) dfs(cur.left, level + 1);
        if (cur.right != null) dfs(cur.right, level + 1);
    }
}
