package sulqn;

/*
二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。

路径和 是路径中各节点值的总和。

给你一个二叉树的根节点 root ，返回其 最大路径和 。
 */

public class L124 {
    int ans = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        int x = dfs(root, true);
        int y = dfs(root, false);
        ans = Math.max(ans, x + y - root.val);
        return ans;
    }

    private int dfs(TreeNode root, boolean left) {
        if (root == null) return 0;
        if (left) {
            if (root.left == null) return root.val;
            else {
                int x = dfs(root.left, true);
                int y = dfs(root.left, false);
                ans = Math.max(ans, x + y - root.left.val);
                return root.val + Math.max(0, Math.max(x, y));
            }
        } else {
            if (root.right == null) return root.val;
            else {
                int x = dfs(root.right, true);
                int y = dfs(root.right, false);
                ans = Math.max(ans, x + y - root.right.val);
                return root.val + Math.max(0, Math.max(x, y));
            }
        }
    }
}
