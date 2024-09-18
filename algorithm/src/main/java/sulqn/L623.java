package sulqn;

public class L623 {
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        TreeNode dummy = new TreeNode(-1);
        dummy.left = root;
        dfs(dummy, depth, 0, val);
        return dummy.left;
    }

    private void dfs(TreeNode cur, int depth, int curDepth, int val) {
        if (curDepth == depth - 1) {
            if (cur.left != null) {
                TreeNode left = cur.left;
                TreeNode l = new TreeNode(val);
                l.left = left;
                cur.left = l;
            } else {
                TreeNode l = new TreeNode(val);
                cur.left = l;
            }
            if (cur.right != null) {
                TreeNode right = cur.right;
                TreeNode r = new TreeNode(val);
                r.right = right;
                cur.right = r;
            } else {
                TreeNode r = new TreeNode(val);
                cur.right = r;
            }
        } else {
            if (cur.left != null) {
                dfs(cur.left, depth, curDepth + 1, val);
            }
            if (cur.right != null) {
                dfs(cur.right, depth, curDepth + 1, val);
            }
        }
    }
}
