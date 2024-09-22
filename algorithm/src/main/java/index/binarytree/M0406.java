package index.binarytree;

import sulqn.TreeNode;

import java.util.Stack;

/*
设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。

如果指定节点没有对应的“下一个”节点，则返回null。
 */

public class M0406 {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode x = root;
        while (x != null) {
            stack.add(x);
            x = x.left;
        }
        boolean find = false;
        while (!stack.isEmpty()) {
            TreeNode y = stack.pop();
            if (find) return y;
            if (y == p) find = true;
            System.out.println(y.val);
            if (y.right != null) {
                TreeNode z = y.right;
                while (z != null) {
                    stack.add(z);
                    z = z.left;
                }
            }
        }
        return null;
    }
}
