package linshen.binarytree;

import sulqn.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 */

public class L94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null)return ans;
        else {
            if(root.left != null)ans.addAll(inorderTraversal(root.left));
            ans.add(root.val);
            if(root.right != null)ans.addAll(inorderTraversal(root.right));
            return ans;
        }
    }

    //todo 出栈的时候进list，左边的先进栈，然后中间，最后右边。但是右边的进栈之前要先让其所有左节点进栈
    public List<Integer> inorder(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null)return ans;
        else {
            Stack<TreeNode> stack = new Stack<>();
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            while (!stack.isEmpty()) {
                TreeNode x = stack.pop();
                ans.add(x.val);
                TreeNode y = x.right;
                while (y != null){
                    stack.add(y);
                    y = y.left;
                }
            }
            return ans;
        }
    }
}
