package index.dfs;

import sulqn.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。

叶子节点 是指没有子节点的节点。
 */

public class L113 {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        dfs(targetSum, new LinkedList<>(), root, 0, ans);
        return ans;
    }

    private void dfs(int targetSum, LinkedList<Integer> path, TreeNode cur, int sum, List<List<Integer>> ans) {
        if (cur.left == null && cur.right == null) {
            if (sum + cur.val == targetSum) {
                path.addLast(cur.val);
                ans.add(new ArrayList<>(path));
                path.removeLast();
            }
        } else {
            if (cur.left != null) {
                path.addLast(cur.val);
                dfs(targetSum, path, cur.left, sum + cur.val, ans);
                path.removeLast();
            }

            if (cur.right != null) {
                path.addLast(cur.val);
                dfs(targetSum, path, cur.right, sum + cur.val, ans);
                path.removeLast();
            }
        }
    }
}
