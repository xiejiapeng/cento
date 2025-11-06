package linshen.binarytree;

import sulqn.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 */

public class L144 {
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null)return ans;
        else {
            ans.add(root.val);
            ans.addAll(preorderTraversal2(root.left));
            ans.addAll(preorderTraversal2(root.right));
            return ans;
        }
    }

    //todo 前序遍历。因为子节点只能被父节点引用，所以父节点肯定需要先遍历到。这种情况下，如果遍历的时候就加入到list中，顺序就反了（因为是栈）。所以只能是出栈的时候弹入子节点，同时加入到list中
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null)return ans;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode x = stack.pop();
            ans.add(x.val);
            if(x.right != null)stack.add(x.right);
            if(x.left != null)stack.add(x.left);
        }
        return ans;
    }
}
