package linshen.binarytree;

import sulqn.TreeNode;

/*
给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，
并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。

一般来说，删除节点可分为两个步骤：

首先找到需要删除的节点；
如果找到了，删除它
 */

public class L450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null)return null;
        TreeNode dummy = new TreeNode(100001);
        dummy.left = root;
        dfs(dummy, key);
        return dummy.left;
    }

    private void dfs(TreeNode root, int key) {
        if(root.left != null) {
            if(root.left.val == key) {
                TreeNode l = root.left;
                if(l.left == null && l.right == null)root.left = null;
                else if(l.left == null) root.left = l.right;
                else if(l.right == null) root.left = l.left;
                else {
                    root.left = l.right;
                    TreeNode lr = l.right;
                    while (lr.left != null)lr = lr.left;
                    lr.left = l.left;
                }
            } else {
                dfs(root.left, key);
            }
        }

        if(root.right != null) {
            if(root.right.val == key) {
                TreeNode r = root.right;
                if(r.left == null && r.right == null)root.right = null;
                else if(r.left == null) root.right = r.right;
                else if(r.right == null) root.right = r.left;
                else {
                    root.right = r.right;
                    TreeNode rr = r.right;
                    while (rr.left != null)rr = rr.left;
                    rr.left = r.left;
                }
            } else {
                dfs(root.right, key);
            }
        }
    }
}
