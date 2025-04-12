package linshen.binarytree;


/*
给你一棵二叉树，它的根为 root 。请你删除 1 条边，使二叉树分裂成两棵子树，且它们子树和的乘积尽可能大。
由于答案可能会很大，请你将结果对 10^9 + 7 取模后再返回。
 */

import sulqn.TreeNode;

public class L1339 {
    int mod = (int)(1e9+7);
    long ans = Long.MIN_VALUE;
    public int maxProduct(TreeNode root) {
        long sum = sum(root);
        dfs(root, sum);
        return (int)(ans%mod);
    }

    private long sum(TreeNode root) {
        if(root == null)return 0;
        else return root.val + sum(root.left) + sum(root.right);
    }

    private long dfs(TreeNode root, long sum) {
        if(root == null)return 0;
        else {
            long x = dfs(root.left, sum);
            long y = dfs(root.right, sum);
            long s = (root.val + x + y);
            long left = sum - s;
            long multi = s * left;

            //todo h：不能先mod，再max！！
            ans = Math.max(ans, multi);

            return s;
        }
    }
}
