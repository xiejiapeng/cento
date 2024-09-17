package index.bfs;

/*
给你二叉搜索树的根节点 root ，
该树中的 恰好 两个节点的值被错误地交换。请在不改变其结构的情况下，恢复这棵树 。

1 2 3 4 5

1 5 3 4 2
 */

import sulqn.TreeNode;

import java.util.Stack;

public class L99 {
    TreeNode a = null;
    TreeNode b = null;
    TreeNode c = null;
    TreeNode d = null;

    public void recoverTree(TreeNode root) {
        inode(root);
        if (c == null) {
            int tmp = a.val;
            a.val = b.val;
            b.val = tmp;
        } else {
            int tmp = a.val;
            a.val = d.val;
            d.val = tmp;
        }
    }

    public void inode(TreeNode root) {
        TreeNode last = null;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null) {
            stack.add(root);
            root = root.left;
        }
        while (!stack.isEmpty()) {
            TreeNode x = stack.pop();
            if (last == null) {
                last = x;
            } else {
                if (last.val > x.val) {
                    if (a == null) {
                        a = last;
                        b = x;
                    } else {
                        c = last;
                        d = x;
                    }
                }
                last = x;
            }

            TreeNode right = x.right;
            while (right != null) {
                stack.add(right);
                right = right.left;
            }
        }
    }
}
