package linshen.binarytree;

import sulqn.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/*
给你一个二叉树的根节点 root。设根节点位于二叉树的第 1 层，
而根节点的子节点位于第 2 层，依此类推。
请返回层内元素之和 最大 的那几层（可能只有一层）的层号，
并返回其中 最小 的那个。
 */

public class L1161 {
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        int max = Integer.MIN_VALUE;
        int ans = 1;
        int id = 0;
        queue.add(root);
        while (!queue.isEmpty()){
            id++;
            int n = queue.size();
            int sum = 0;
            while (n-->0) {
                TreeNode x = queue.poll();
                if(x.left != null)queue.add(x.left);
                if(x.right != null)queue.add(x.right);
                sum += x.val;
            }
            if(sum > max) {
                max = sum;
                ans = id;
            }
        }
        return ans;
    }
}
