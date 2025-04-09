package linshen.binarytree;

import sulqn.TreeNode;
/*
给你一棵二叉树的根节点 root ，找出并返回满足要求的节点数，要求节点的值等于其 子树 中值的 平均值 。
注意：
n 个元素的平均值可以由 n 个元素 求和 然后再除以 n ，并 向下舍入 到最近的整数。
root 的 子树 由 root 和它的所有后代组成。
 */

public class L2265 {
    int ans = 0;
    public int averageOfSubtree(TreeNode root) {
        if(root != null)dfs(root);
        return ans;
    }

    private int[] dfs(TreeNode root) {
        int[] r = new int[2];
        r[0] = 1;
        r[1] = root.val;
        if (root.left != null || root.right != null) {
            if(root.left != null) {
                int[] lr = dfs(root.left);
                r[0] += (lr[0] + 1);
                r[1] += (lr[1] + root.left.val);
            }
            if(root.right != null) {
                int[] rr = dfs(root.right);
                r[0] += (rr[0] + 1);
                r[1] += (rr[1] + root.right.val);
            }
        }
        System.out.println("root="+root.val+",r[0]="+r[0]+",r[1]="+r[1]);
        if(r[0] != 0 && root.val == r[1] / r[0])ans++;
        return r;
    }
}
