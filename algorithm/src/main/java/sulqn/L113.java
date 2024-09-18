package sulqn;

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
        LinkedList<Integer> l = new LinkedList<>();
        l.add(root.val);
        dfs(root, l, targetSum, root.val);
        return ans;
    }

    private void dfs(TreeNode cur, LinkedList<Integer> list, int target, int sum) {
        if (cur.left == null && cur.right == null) {
            if (sum == target) {
                ans.add(new ArrayList<>(list));
            }
        } else {
            if (cur.left != null) {
                list.addLast(cur.left.val);
                dfs(cur.left, list, target, sum + cur.left.val);
                list.removeLast();
            }
            if (cur.right != null) {
                list.addLast(cur.right.val);
                dfs(cur.right, list, target, sum + cur.right.val);
                list.removeLast();
            }
        }
    }
}
