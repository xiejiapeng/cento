package sulwish;

import sulqn.TreeNode;

/*
给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。

假设二叉树中至少有一个节点。
 */

public class L513 {
    public int findBottomLeftValue(TreeNode root) {
        return find(root, 0)[1];
    }

    public int[] find(TreeNode root, int cur) {
        if (root.left == null && root.right == null) {
            return new int[]{cur, root.val};
        } else if (root.left == null) {
            return find(root.right, cur + 1);
        } else if (root.right == null) {
            return find(root.left, cur + 1);
        } else {
            int[] left = find(root.left, cur + 1);
            int[] right = find(root.right, cur + 1);
            if (left[0] >= right[0]) return left;
            else return right;
        }
    }
}
