package sulwish;

import sulqn.TreeNode;

/*
给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。

一般来说，删除节点可分为两个步骤：

首先找到需要删除的节点；
如果找到了，删除它。
 */

public class L450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        TreeNode dummy = new TreeNode(-1);
        dummy.left = root;
        dfs(root, dummy, key);
        return dummy.left;
    }

    private void dfs(TreeNode cur, TreeNode last, int key) {
        if (cur.val == key) {
            if (cur.left == null && cur.right == null) {
                if (cur == last.left) {
                    last.left = null;
                } else {
                    last.right = null;
                }
            } else if (cur.left == null) {
                if (cur == last.left) {
                    last.left = cur.right;
                } else {
                    last.right = cur.right;
                }
            } else if (cur.right == null) {
                if (cur == last.left) {
                    last.left = cur.left;
                } else {
                    last.right = cur.left;
                }
            } else {
                TreeNode tmp = cur.left;
                while (tmp.right != null) {
                    tmp = tmp.right;
                }
                tmp.right = cur.right;
                if (cur == last.left) {
                    last.left = cur.left;
                } else {
                    last.right = cur.left;
                }
            }
        } else {
            if (cur.left != null) dfs(cur.left, cur, key);
            if (cur.right != null) dfs(cur.right, cur, key);
        }
    }
}
