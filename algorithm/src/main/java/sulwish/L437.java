package sulwish;
/*
给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。

路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 */

import sulqn.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class L437 {
    int cnt = 0;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

        System.out.println(new L437().pathSum(root, 0));
    }

    public int pathSum(TreeNode root, int targetSum) {
        dfs(root, new HashMap<>(), 0, targetSum);
        return cnt;
    }

    private void dfs(TreeNode node, Map<Long, Integer> total, long sum, long targetSum) {
        if (node == null) return;
        else {
            long curSum = sum + node.val;
            if (curSum == targetSum) cnt++;

            long target = curSum - targetSum;
            if (total.containsKey(target)) cnt += total.get(target);
            total.put(curSum, total.getOrDefault(curSum, 0) + 1);

            dfs(node.left, total, curSum, targetSum);
            dfs(node.right, total, curSum, targetSum);

            int c = total.get(curSum);
            if (c == 1) total.remove(curSum);
            else total.put(curSum, total.get(curSum) - 1);
        }
    }
}
