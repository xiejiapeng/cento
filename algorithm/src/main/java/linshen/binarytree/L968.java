package linshen.binarytree;

import sulqn.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*
给定一个二叉树，我们在树的节点上安装摄像头。
节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
计算监控树的所有节点所需的最小摄像头数量。

        1a
             2b
          3c       4d
               5e     6f
 */

public class L968 {
    Map<TreeNode, Integer> include = new HashMap<>();
    Map<TreeNode, Integer> exclude = new HashMap<>();
    public int minCameraCover(TreeNode root) {
        int t =  f(root,true);
        return t;
    }

    private int f(TreeNode root, boolean in) {
        if(root == null)return 0;
        else {
            if(in) {
                if(include.containsKey(root))return include.get(root);
                else {
                    int x = 1;
                    if(root.left != null) {
                        x += Math.min(f(root.left.left, true), f(root.left.left,false));
                        x += Math.min(f(root.left.right, true), f(root.left.right,false));
                    }
                    if(root.right != null) {
                        x += Math.min(f(root.right.left, true), f(root.right.left,false));
                        x += Math.min(f(root.right.right, true), f(root.right.right,false));
                    }
                    include.put(root, x);
                    return x;
                }
            } else {
                if(exclude.containsKey(root))return exclude.get(root);
                //include root.left
                int x;
                if(root.left == null && root.right == null) x = Integer.MAX_VALUE;
                else if(root.left == null) {
                    x = f(root.right, true);
                } else if(root.right == null) {
                    x = f(root.left, true);
                } else {
                    int u = f(root.left, true) + Math.min(f(root.right, true), f(root.right, false));
                    int v = f(root.right, true) + Math.min(f(root.left, true), f(root.left, false));
                    x = Math.min(u, v);
                }
                exclude.put(root, x);
                return x;
            }
        }
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        TreeNode d = new TreeNode(4);
        TreeNode e = new TreeNode(5);
        TreeNode f = new TreeNode(6);
        a.right = b;
        b.left = c;
        b.right = d;
        d.left = e;
        d.right = f;
        System.out.println(new L968().minCameraCover(d));
    }
}
