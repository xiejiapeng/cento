package linshen.binarytree;

import sulqn.TreeNode;

/*
给你一个有根节点 root 的二叉树，返回它 最深的叶节点的最近公共祖先 。

回想一下：

叶节点 是二叉树中没有子节点的节点
树的根节点的 深度 为 0，如果某一节点的深度为 d，那它的子节点的深度就是 d+1
如果我们假定 A 是一组节点 S 的 最近公共祖先，S 中的每个节点都在以 A 为根节点的子树中，且 A 的深度达到此条件下可能的最大值。
 */

public class LL123 {
    int cnt = 0;
    TreeNode ans = null;

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        int height = height(root);
        count(root, 1, height);
        dfs(root, 1, height);
        return ans;
    }

    private int dfs(TreeNode root, int h, int height) {
        if (root == null) return 0;
        int a = 0;
        if (root.left == null && root.right == null) {
            if (h == height) a = 1;
        } else {
            int l = dfs(root.left, h + 1, height);
            int r = dfs(root.right, h + 1, height);
            a = l + r;
        }
        if (a == cnt && ans == null) {
            ans = root;
        }
        return a;
    }

    private int height(TreeNode root) {
        if (root == null) return 0;
        else return 1 + Math.max(height(root.left), height(root.right));
    }

    private void count(TreeNode root, int h, int height) {
        if (h == height) cnt++;
        else {
            if (root.left != null) count(root.left, h + 1, height);
            if (root.right != null) count(root.right, h + 1, height);
        }
    }
}
