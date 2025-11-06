package linshen.binarytree;

import sulqn.TreeNode;

public class L112 {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return visit(root, targetSum);
    }

    public boolean visit(TreeNode root, long targetSum) {
        if(root == null)return false;
        if(root.left == null && root.right == null) {
            return root.val == targetSum;
        } else {
            boolean ans = false;
            if(root.left != null) {
                ans |= visit(root.left, targetSum - root.val);
            }
            if(root.right != null) {
                ans |= visit(root.right, targetSum - root.val);
            }
            return ans;
        }
    }
}
