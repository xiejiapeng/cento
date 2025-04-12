package linshen.binarytree;

import sulqn.TreeNode;

/*
给你一棵以 root 为根的二叉树，二叉树中的交错路径定义如下：

选择二叉树中 任意 节点和一个方向（左或者右）。
如果前进方向为右，那么移动到当前节点的的右子节点，否则移动到它的左子节点。
改变前进方向：左变右或者右变左。
重复第二步和第三步，直到你在树中无法继续移动。
交错路径的长度定义为：访问过的节点数目 - 1（单个节点的路径长度为 0 ）。

请你返回给定树中最长 交错路径 的长度。
 */

public class L1372 {
    int max = -1;
    public int longestZigZag(TreeNode root) {
        dfs(root,true);
        dfs(root,false);
        return max;
    }

    private int dfs(TreeNode root, boolean left) {
        int ans;
        if(left) {
            if(root.left == null)ans = 0;
            else {
                int x = dfs(root.left, false);
                //todo 子节点多尝试一个方向，否则不能遍历所有的情况
                dfs(root.left, true);
                ans = x+1;
            }
        } else {
            if(root.right == null)ans = 0;
            else {
                int x = dfs(root.right, true);
                dfs(root.right, false);
                ans = x + 1;
            }
        }
        max = Math.max(ans, max);
        return ans;
    }
}
