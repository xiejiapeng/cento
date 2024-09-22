package index.binarytree;

import sulqn.TreeNode;

public class L235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return find(root, p, q);
    }

    private TreeNode find(TreeNode x, TreeNode p, TreeNode q) {
        if (x == p) {
            return p;
        } else if (x == q) {
            return q;
        } else {
            if (x.left == null && x.right == null) {
                return null;
            } else if (x.left == null) {
                return find(x.right, p, q);
            } else if (x.right == null) {
                return find(x.left, p, q);
            } else {
                TreeNode a = find(x.left, p, q);
                TreeNode b = find(x.right, p, q);
                if (a != null && b != null) return x;
                else if (a != null) {
                    return a;
                } else if (b != null) {
                    return b;
                } else return null;
            }
        }
    }
}
