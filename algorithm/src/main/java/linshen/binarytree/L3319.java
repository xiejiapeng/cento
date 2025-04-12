package linshen.binarytree;


/*
给你一棵 二叉树 的根节点 root 和一个整数k。
返回第 k 大的 完美二叉子树 的大小，如果不存在则返回 -1。
完美二叉树 是指所有叶子节点都在同一层级的树，且每个父节点恰有两个子节点。
 */

import sulqn.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class L3319 {
    List<Integer> ans = new ArrayList<>();
    public int kthLargestPerfectSubtree(TreeNode root, int k) {
        dfs(root);
        Collections.sort(ans);
        return ans.size() - k >= 0 ? ans.get(ans.size() - k) : -1;
    }

    private int dfs(TreeNode root) {
        int h = 0;
        if(root.left == null && root.right == null){
            h = 1;
        } else if(root.left == null || root.right == null) {
            if(root.left != null)dfs(root.left);
            if(root.right != null)dfs(root.right);
            h = 0;
        } else {
            int x = dfs(root.left);
            int y = dfs(root.right);
            if(x != y || x == 0 || y == 0)h = 0;
            else {
                h = x + 1;
            }
        }
        if(h > 0){
            ans.add((int)Math.pow(2, h) - 1);
            System.out.println("root="+root.val+",hei="+h);
        }

        return h;
    }
}
