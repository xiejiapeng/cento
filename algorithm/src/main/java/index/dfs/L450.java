package index.dfs;

import sulqn.TreeNode;

/*
给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。

一般来说，删除节点可分为两个步骤：

首先找到需要删除的节点；
如果找到了，删除它。
 */

public class L450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null)return null;
        TreeNode dummy = new TreeNode(root.val + 1);
        dummy.left = root;
        dfs(dummy, root, key);
        return dummy.left;
    }

    private void dfs(TreeNode parent, TreeNode cur, int key) {
        if(cur == null)return;
        if(cur.val == key) {
            if(cur == parent.left) {
                if(cur.left == null && cur.right == null) {
                    parent.left = null;
                } else if(cur.left == null) {
                    parent.left = cur.right;
                } else if(cur.right == null) {
                    parent.left = cur.left;
                } else {
                    parent.left = cur.left;
                    TreeNode x = cur.left;
                    while (x.right != null) {
                        x = x.right;
                    }
                    x.right = cur.right;
                    cur.right = null;
                }
            } else if(cur == parent.right) {
                if(cur.left == null && cur.right == null) {
                    parent.right = null;
                } else if(cur.left == null) {
                    parent.right = cur.right;
                } else if(cur.right == null) {
                    parent.right = cur.left;
                } else {
                    parent.right = cur.left;
                    TreeNode x = cur.left;
                    while (x.right != null) {
                        x = x.right;
                    }
                    x.right = cur.right;
                    cur.right = null;
                }
            }
        } else {
            dfs(cur, cur.right, key);
            dfs(cur, cur.left, key);
        }
    }
}
