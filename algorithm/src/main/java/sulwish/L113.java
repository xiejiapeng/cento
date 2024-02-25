package sulwish;

import sulqn.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。

叶子节点 是指没有子节点的节点。
 */
public class L113 {
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) return ans;
        LinkedList<Integer> path = new LinkedList<>();
        path.addLast(root.val);
        dfs(root, root, root.val, targetSum, path);
        return ans;
    }

    public void dfs(TreeNode root, TreeNode cur, int curSum, int targetSum, LinkedList<Integer> path) {
        if (cur.left == null && cur.right == null) {
            if (curSum == targetSum) {
                ans.add(new ArrayList<>(path));
            }
            return;
        } else {
            if (cur.left != null) {
                path.addLast(cur.left.val);
                dfs(root, cur.left, curSum + cur.left.val, targetSum, path);
                path.removeLast();
            }

            if (cur.right != null) {
                path.addLast(cur.right.val);
                dfs(root, cur.right, curSum + cur.right.val, targetSum, path);
                path.removeLast();
            }
        }
    }
}
