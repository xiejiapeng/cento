package index.binarytree;

import sulqn.TreeNode;

/*
给你二叉树的根结点 root ，此外树的每个结点的值要么是 0 ，要么是 1 。

返回移除了所有不包含 1 的子树的原二叉树。

节点 node 的子树为 node 本身加上所有 node 的后代。
 */

public class L814 {
    public TreeNode pruneTree(TreeNode root) {
        return dfs(root);
    }

    private TreeNode dfs(TreeNode root) {
        if (root == null) return null;
        else if (root.left == null && root.right == null) {
            if (root.val == 0) return null;
            else return root;
        } else if (root.left == null) {
            TreeNode r = dfs(root.right);
            if (r == null) {
                root.right = null;
                return root.val == 0 ? null : root;
            } else {
                root.right = r;
                return root;
            }
        } else if (root.right == null) {
            TreeNode l = dfs(root.left);
            if (l == null) {
                root.left = null;
                return root.val == 0 ? null : root;
            } else {
                root.left = l;
                return root;
            }
        } else {
            TreeNode l = dfs(root.left);
            TreeNode r = dfs(root.right);
            root.left = l;
            root.right = r;
            if (l == null && r == null) {
                return root.val == 0 ? null : root;
            } else {
                return root;
            }
        }
    }
}
