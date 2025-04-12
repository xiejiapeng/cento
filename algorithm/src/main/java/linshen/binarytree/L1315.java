package linshen.binarytree;

import sulqn.TreeNode;

/*
给你一棵二叉树，请你返回满足以下条件的所有节点的值之和：
该节点的祖父节点的值为偶数。（一个节点的祖父节点是指该节点的父节点的父节点。）
如果不存在祖父节点值为偶数的节点，那么返回 0 。
 */

public class L1315 {
    int ans = 0;
    public int sumEvenGrandparent(TreeNode root) {
        dfs(root, null, null);
        return ans;
    }

    private void dfs(TreeNode root, TreeNode p, TreeNode g) {
        if(g != null && g.val % 2 == 0)ans += root.val;
        if(root.left != null) {
            dfs(root.left, root, p);
        }
        if(root.right != null) {
            dfs(root.right, root, p);
        }
    }
}
