package sulqn;

/*
给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 */

public class L235 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        TreeNode left = new TreeNode(1);
        root.left = left;
        System.out.println(new L235().lowestCommonAncestor(root, root, left).val);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return find(root, p, q);
    }

    //if ancestor of p and q is in the subtree of root, return that
    //if only p is in the subtree of root, return p
    //if only q is in the subtree of root, return q
    //otherwise return null
    public TreeNode find(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        else {
            if (root.left == null && root.right == null) {
                if (root == p) return p;
                else if (root == q) return q;
                else return null;
            } else {
                TreeNode l = find(root.left, p, q);
                TreeNode r = find(root.right, p, q);
                if (l == null && r == null) {
                    if (root == p || root == q) return root;
                    else return null;
                } else if (l != null && r != null) {
                    return root;
                } else if (l != null) {
                    if (root == p || root == q) {
                        return root;
                    } else return l;
                } else {
                    if (root == p || root == q) {
                        return root;
                    } else return r;
                }
            }
        }
    }
}
