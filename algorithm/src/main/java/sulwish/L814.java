package sulwish;

import sulqn.TreeNode;

/*
给你二叉树的根结点 root ，此外树的每个结点的值要么是 0 ，要么是 1 。

返回移除了所有不包含 1 的子树的原二叉树。

节点 node 的子树为 node 本身加上所有 node 的后代。
 */

public class L814 {
    public TreeNode pruneTree(TreeNode root) {
        TreeNode dummy = new TreeNode(1);
        dummy.left = root;
        boolean all = allZero(root, dummy);
        return dummy.left;
    }

    private boolean allZero(TreeNode cur, TreeNode last) {
        if (cur == null) {
            return true;
        } else {
            boolean l = allZero(cur.left, cur);
            if (l) cur.left = null;
            boolean r = allZero(cur.right, cur);
            if (r) cur.right = null;

            if (l && r && cur.val == 0) {
                if (last.left == cur) last.left = null;
                else last.right = null;
                return true;
            } else {
                return false;
            }
        }
    }
}
