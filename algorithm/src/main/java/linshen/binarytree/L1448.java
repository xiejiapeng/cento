package linshen.binarytree;

import sulqn.TreeNode;

/*
给你一棵根为 root 的二叉树，请你返回二叉树中好节点的数目。
「好节点」X 定义为：从根到该节点 X 所经过的节点中，没有任何节点的值大于 X 的值。
 */

public class L1448 {
    int ans = 0;
    public int goodNodes(TreeNode root) {
        visit(root, Integer.MIN_VALUE);
        return ans;
    }

    private void visit(TreeNode root, int max) {
        if(root != null) {
            if(root.val >= max)ans++;
            if(root.left != null) {
                visit(root.left, Math.max(max, root.val));
            }
            if(root.right != null) {
                visit(root.right, Math.max(max, root.val));
            }
        }
    }
}
