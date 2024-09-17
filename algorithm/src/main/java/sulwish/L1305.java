package sulwish;

import sulqn.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
给你 root1 和 root2 这两棵二叉搜索树。请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。.
 */

public class L1305 {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        Stack<TreeNode> in1 = new Stack<>();
        Stack<TreeNode> in2 = new Stack<>();

        List<Integer> ans = new ArrayList<>();

        while (root1 != null) {
            in1.add(root1);
            root1 = root1.left;
        }

        while (root2 != null) {
            in2.add(root2);
            root2 = root2.left;
        }

        while (!in1.isEmpty() || !in2.isEmpty()) {
            if (in1.isEmpty()) {
                ans.add(get(in2));
            } else if (in2.isEmpty()) {
                ans.add(get(in1));
            } else {
                if (in1.peek().val <= in2.peek().val) {
                    ans.add(get(in1));
                } else {
                    ans.add(get(in2));
                }
            }
        }

        return ans;
    }

    private int get(Stack<TreeNode> stack) {
        TreeNode x = stack.pop();
        TreeNode right = x.right;
        while (right != null) {
            stack.add(right);
            right = right.left;
        }
        return x.val;
    }
}
