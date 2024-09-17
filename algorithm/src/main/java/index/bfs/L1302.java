package index.bfs;

import sulqn.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*
给你一棵二叉树的根节点 root ，请你返回 层数最深的叶子节点的和 。
 */

public class L1302 {
    public int deepestLeavesSum(TreeNode root) {
        Map<Integer, Integer> sum = new HashMap<>();
        dfs(root, sum, 1);
        int dep = sum.keySet().stream().mapToInt(i -> i).max().getAsInt();
        return sum.get(dep);
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
