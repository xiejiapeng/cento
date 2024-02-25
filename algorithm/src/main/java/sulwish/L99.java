package sulwish;

import sulqn.TreeNode;

public class L99 {
    TreeNode a = null;
    TreeNode b = null;
    TreeNode last = null;

    public void recoverTree(TreeNode root) {
        dfs(root);
        int tmp = a.val;
        a.val = b.val;
        b.val = tmp;
    }

    public void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        if (last != null && last.val > node.val) {
            if (a == null) {
                a = last;
                b = node;
            } else {
                b = node;
            }
        }
        last = node;
        dfs(node.right);
    }


}
