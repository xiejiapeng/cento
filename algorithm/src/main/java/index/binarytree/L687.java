package index.binarytree;

import sulqn.TreeNode;

/*
给定一个二叉树的 root ，返回 最长的路径的长度 ，这个路径中的 每个节点具有相同值 。 这条路径可以经过也可以不经过根节点。

两个节点之间的路径长度 由它们之间的边数表示。
 */

public class L687 {
    int max = 1;

    public static void main(String[] args) {
        TreeNode a = new TreeNode(5);
        TreeNode b = new TreeNode(5);
        TreeNode c = new TreeNode(5);
        TreeNode d = new TreeNode(4);
        a.left = d;
        a.right = b;
        b.right = c;
        System.out.println(new L687().longestUnivaluePath(a));
    }

    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return max - 1;
    }

    private int[] dfs(TreeNode root) {
        if (root == null) return null;
        else {
            if (root.left == null && root.right == null) {
                return new int[]{1, 1};
            } else if (root.left == null) {
                int[] r = dfs(root.right);
                if (root.right.val == root.val) {
                    int m = Math.max(r[0], r[1]);
                    max = Math.max(max, 1 + m);
                    return new int[]{1, 1 + m};
                } else {
                    return new int[]{1, 1};
                }
            } else if (root.right == null) {
                int[] l = dfs(root.left);
                if (root.left.val == root.val) {
                    int m = Math.max(l[0], l[1]);
                    max = Math.max(max, 1 + m);
                    return new int[]{1 + m, 1};
                } else {
                    return new int[]{1, 1};
                }
            } else {
                int[] l = dfs(root.left);
                int[] r = dfs(root.right);
                int lm = root.left.val == root.val ? Math.max(l[0], l[1]) : 0;
                int rm = root.right.val == root.val ? Math.max(r[0], r[1]) : 0;
                max = Math.max(max, 1 + lm + rm);
                return new int[]{1 + lm, 1 + rm};
            }
        }
    }
}
