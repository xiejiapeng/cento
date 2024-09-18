package sulwish;

import sulqn.TreeNode;

public class L1302 {
    int maxDep = -1;
    int sum = 0;

    public int deepestLeavesSum(TreeNode root) {
        dfs(root, 0);
        return sum;
    }

    private void dfs(TreeNode node, int level) {
        if (node.left == null && node.right == null) {
            if (level == maxDep) {
                sum += node.val;
            } else if (level > maxDep) {
                maxDep = level;
                sum = node.val;
            }
        } else {
            if (node.left != null) dfs(node.left, level + 1);
            if (node.right != null) dfs(node.right, level + 1);
        }
    }


}
