package linshen.binarytree;

import sulqn.TreeNode;

/*
给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 */

public class L235 {
    TreeNode ans;

    public static void main(String[] args) {
        TreeNode a = new TreeNode(2);
        TreeNode b = new TreeNode(1);
        a.left = b;
        System.out.println(new L235().lowestCommonAncestor(a, a, b));
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        find(root, p, q);
        return ans;
    }

    private TreeNode find(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        TreeNode l = find(root.left, p, q);
        TreeNode r = find(root.right, p, q);
        if (root == p || root == q) {
            if ((root == p && l == q) || (root == p && r == q) || (root == q && l == p) || (root == q && r == p)) {
                ans = root;
            }
            return root;
        } else {

            if (l == null && r == null) return null;
            else if (l == null) return r;
            else if (r == null) return l;
            else {
                ans = root;
                return null;
            }
        }
    }
}
