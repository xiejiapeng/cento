package index.dfs;

import sulqn.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*
给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。

路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 */

public class L437 {
    int ans = 0;
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Long> all = new HashMap<>();
        if(root == null)return 0;
        dfs(root, root.val, all, (long)targetSum);
        return ans;
    }

    private void dfs(TreeNode cur, long sum, Map<Long, Long> all, long targetSum) {
        if(sum == targetSum) ans += 1;
        long target = sum - targetSum;
        ans += all.getOrDefault(target, 0l);
        all.put(sum, all.getOrDefault(sum, 0l) + 1);
        if(cur.left != null) {
            long s = sum + cur.left.val;
            dfs(cur.left, s, all, targetSum);
        }

        if(cur.right != null) {
            long s = sum + cur.right.val;
            dfs(cur.right, s, all, targetSum);
        }
        all.put(sum, all.getOrDefault(sum, 0l) - 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode l = new TreeNode(-2);
        TreeNode r = new TreeNode(-3);
        root.left = l;
        root.right = r;
        System.out.println(new L437().pathSum(root, -1));
    }
}
