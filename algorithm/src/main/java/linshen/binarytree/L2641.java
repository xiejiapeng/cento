package linshen.binarytree;

import sulqn.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/*
给你一棵二叉树的根 root ，请你将每个节点的值替换成该节点的所有 堂兄弟节点值的和 。
如果两个节点在树中有相同的深度且它们的父节点不同，那么它们互为 堂兄弟 。
请你返回修改值之后，树的根 root 。
注意，一个节点的深度指的是从树根节点到这个节点经过的边数。
 */

public class L2641 {
    Map<TreeNode, TreeNode> parent = new HashMap<>();
    public TreeNode replaceValueInTree(TreeNode root) {
        dfs(root);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        Map<Integer,Integer> ls = new HashMap<>();
        int level = 0;
        while (!queue.isEmpty()) {
            level++;
            int sum = 0;
            int n = queue.size();
            while (n-- > 0) {
                TreeNode x = queue.poll();
                sum += x.val;
                if(x.left != null)queue.add(x.left);
                if(x.right != null)queue.add(x.right);
            }
            ls.put(level, sum);
        }

        level = 0;
        queue.add(root);
        while (!queue.isEmpty()) {
            level++;
            int n = queue.size();
            while (n-- > 0) {
                TreeNode x = queue.poll();
                int sum = ls.get(level);
                if(parent.containsKey(x)) {
                    TreeNode p = parent.get(x);
                    if(x == p.left) {
                        sum -= x.val;
                        if(p.right != null) sum -= p.right.val;
                        x.val = sum;
                    } else {
                        if(p.left != null)x.val = p.left.val;
                        else x.val = (sum - x.val);
                    }
                } else {
                    x.val = sum - x.val;
                }
                if(x.left != null)queue.add(x.left);
                if(x.right != null)queue.add(x.right);
            }
        }
        return root;
    }

    private void dfs(TreeNode root) {
        if(root == null)return;
        else {
            if(root.left != null) {
                parent.put(root.left, root);
                dfs(root.left);
            }
            if(root.right != null) {
                parent.put(root.right, root);
                dfs(root.right);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(3);
        TreeNode b = new TreeNode(1);
        TreeNode c = new TreeNode(2);
        a.left = b;
        a.right = c;
        TreeNode x = new L2641().replaceValueInTree(a);
        System.out.println(x);
    }
}
