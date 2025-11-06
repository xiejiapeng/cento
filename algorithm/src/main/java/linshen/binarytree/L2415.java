package linshen.binarytree;

import sulqn.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
给你一棵 完美 二叉树的根节点 root ，请你反转这棵树中每个 奇数 层的节点值。
例如，假设第 3 层的节点值是 [2,1,3,4,7,11,29,18] ，那么反转后它应该变成 [18,29,11,7,4,3,1,2] 。
反转后，返回树的根节点。

完美 二叉树需满足：二叉树的所有父节点都有两个子节点，且所有叶子节点都在同一层。

节点的 层数 等于该节点到根节点之间的边数。

        1
     2      3
   4   5  6   7
 */

public class L2415 {
    public TreeNode reverseOddLevels(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int id = -1;
        Stack<Integer> stack = new Stack<>();
        stack.add(root.val);
        while (!queue.isEmpty()){
            id++;
            int n = queue.size();
            while (n-->0) {
                TreeNode x = queue.poll();
                if(id % 2 == 0) {
                    if(x.left != null)stack.add(x.left.val);
                    if(x.right != null)stack.add(x.right.val);
                } else {
                    x.val = stack.pop();
                }
                if(x.left != null)queue.add(x.left);
                if(x.right != null)queue.add(x.right);
            }
            System.out.println(stack);
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        a.left = b;
        a.right = c;
        TreeNode x = new L2415().reverseOddLevels(a);
        System.out.println(x);
    }
}
