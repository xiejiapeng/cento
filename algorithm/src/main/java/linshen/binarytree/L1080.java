package linshen.binarytree;

import sulqn.TreeNode;

/*
给你二叉树的根节点 root 和一个整数 limit ，请你同时删除树中所有 不足节点 ，并返回最终二叉树的根节点。
假如通过节点 node 的每种可能的 “根-叶” 路径上值的总和全都小于给定的 limit，则该节点被称之为 不足节点 ，需要被删除。
叶子节点，就是没有子节点的节点。
 */

public class L1080 {
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        TreeNode dummy = new TreeNode(0);
        dummy.left = root;
        dfs(dummy, 0, limit);
        return dummy.left;
    }

    //true means a sufficient node
    private boolean dfs(TreeNode root, int sum, int limit) {
        if (root.left == null && root.right == null) {
            return sum >= limit;
        } else {
            boolean sufficient = false;
            if (root.left != null) {
                boolean ls = dfs(root.left, sum + root.left.val, limit);
                if (!ls) root.left = null;
                sufficient |= ls;
            }
            if (root.right != null) {
                boolean rs = dfs(root.right, sum + root.right.val, limit);
                if (!rs) root.right = null;
                sufficient |= rs;
            }

            return sufficient;
        }
    }
}
