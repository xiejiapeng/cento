package linshen.binarytree;


/*
给你一棵二叉树，它的根为 root 。请你删除 1 条边，使二叉树分裂成两棵子树，且它们子树和的乘积尽可能大。
由于答案可能会很大，请你将结果对 10^9 + 7 取模后再返回。
 */

import sulqn.TreeNode;

public class L1339 {
    int mod = (int)(1e9+7);
    int ans = Integer.MIN_VALUE;
    public int maxProduct(TreeNode root) {
        int sum = sum(root);
        dfs(root, sum);
        return ans;
    }

    private int sum(TreeNode root) {
        if(root == null)return 0;
        else return root.val + sum(root.left) + sum(root.right);
    }

    private long dfs(TreeNode root, int sum) {
        if(root == null)return 0;
        else {
            long x = dfs(root.left, sum);
            long y = dfs(root.right, sum);
            long s = (root.val + x + y);
            long left = sum - s;
            long multi =  s * left;
            ans = Math.max(ans, (int)(multi % mod));
            if(ans == 998864274) {
                System.out.println("sum="+sum+",s="+s+",left="+left);
            }
            return (int)s;
        }
    }
}
