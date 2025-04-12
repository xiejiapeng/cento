package linshen.binarytree;

import sulqn.TreeNode;

import java.util.*;

public class L145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null)return ans;
        Stack<TreeNode> stack = new Stack<>();
        Set<TreeNode> seen = new HashSet<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode x = stack.pop();
            if(seen.contains(x))ans.add(x.val);
            else {
                seen.add(x);
                stack.add(x);
                //todo 注意，先加入right
                if(x.right != null)stack.add(x.right);
                if(x.left != null)stack.add(x.left);

            }
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        TreeNode d = new TreeNode(4);
        TreeNode e = new TreeNode(5);
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        System.out.println(new L145().postorderTraversal(a));
    }
}
