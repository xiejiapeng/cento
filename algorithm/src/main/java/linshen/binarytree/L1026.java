package linshen.binarytree;

import sulqn.TreeNode;

/*
给定二叉树的根节点 root，找出存在于 不同 节点 A 和 B 之间的最大值 V，
其中 V = |A.val - B.val|，且 A 是 B 的祖先。
（如果 A 的任何子节点之一为 B，或者 A 的任何子节点是 B 的祖先，那么我们认为 A 是 B 的祖先）
 */

public class L1026 {

    int ans = Integer.MIN_VALUE;
    public int maxAncestorDiff(TreeNode root) {
        dfs(root, root.val, root.val);
        return ans;
    }

    private void dfs(TreeNode root, int min, int max) {
        int dist = Math.max(Math.abs(root.val - min), Math.abs(root.val - max));
        ans = Math.max(ans, dist);
        if(root.left != null)dfs(root.left, Math.min(min, root.val), Math.max(max, root.val));
        if(root.right != null)dfs(root.right, Math.min(min, root.val), Math.max(max, root.val));
    }
}
