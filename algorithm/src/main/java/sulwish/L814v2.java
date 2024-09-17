package sulwish;

import sulqn.TreeNode;

/*
给你二叉树的根结点 root ，此外树的每个结点的值要么是 0 ，要么是 1 。

返回移除了所有不包含 1 的子树的原二叉树。

节点 node 的子树为 node 本身加上所有 node 的后代。
 */

public class L814v2 {
    public TreeNode pruneTree(TreeNode root) {
        boolean allZero = allZero(root);
        if (allZero) {
            return null;
        } else {
            return root;
        }
    }

    private boolean allZero(TreeNode root) {
        if (root == null) return true;
        else {
            boolean left = allZero(root.left);
            boolean right = allZero(root.right);

            if (left) root.left = null;
            if (right) root.right = null;
            return root.val == 0 && left && right;
        }
    }
}
