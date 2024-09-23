package index.binarytree;

import sulqn.TreeNode;

/*
给定一个二叉搜索树 root (BST)，请将它的每个节点的值替换成树中大于或者等于该节点值的所有节点值之和。

提醒一下， 二叉搜索树 满足下列约束条件：

节点的左子树仅包含键 小于 节点键的节点。
节点的右子树仅包含键 大于 节点键的节点。
左右子树也必须是二叉搜索树。
 */

public class L1038 {
    public TreeNode bstToGst(TreeNode root) {
        if (root == null) return null;
        else {
            if (root.right != null) {
                TreeNode x = bstToGst(root.right);
                root.right = x;
                TreeNode p = x;
                while (p.left != null) {
                    p = p.left;
                }
                root.val += p.val;
            }
            if (root.left != null) {
                TreeNode x = root.left;
                while (x.right != null) {
                    x = x.right;
                }
                x.val += root.val;
                TreeNode y = bstToGst(root.left);
                root.left = y;
            }
            return root;
        }

    }
}
