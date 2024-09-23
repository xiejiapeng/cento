package index.binarytree;

import sulqn.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
给你 root1 和 root2 这两棵二叉搜索树。请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。
 */

public class L1305 {
    public static void main(String[] args) {
        TreeNode x = new TreeNode(2);
        TreeNode y = new TreeNode(1);
        TreeNode z = new TreeNode(4);
        x.left = y;
        x.right = z;

        TreeNode xx = new TreeNode(1);
        TreeNode yy = new TreeNode(0);
        TreeNode zz = new TreeNode(3);
        xx.left = yy;
        xx.right = zz;

        System.out.println(new L1305().getAllElements(x, xx));

    }

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();

        TreeNode p = root1;
        while (p != null) {
            s1.add(p);
            p = p.left;
        }

        TreeNode q = root2;
        while (q != null) {
            s2.add(q);
            q = q.left;
        }

        List<Integer> ans = new ArrayList<>();
        while (!s1.isEmpty() || !s2.isEmpty()) {
            if (s1.isEmpty()) {
                TreeNode x = s2.pop();
                ans.add(x.val);
                TreeNode y = x.right;
                while (y != null) {
                    s2.add(y);
                    y = y.left;
                }
            } else if (s2.isEmpty()) {
                TreeNode x = s1.pop();
                ans.add(x.val);
                TreeNode y = x.right;
                while (y != null) {
                    s1.add(y);
                    y = y.left;
                }
            } else {
                if (s1.peek().val <= s2.peek().val) {
                    TreeNode x = s1.pop();
                    ans.add(x.val);
                    TreeNode y = x.right;
                    while (y != null) {
                        s1.add(y);
                        y = y.left;
                    }
                } else {
                    TreeNode x = s2.pop();
                    ans.add(x.val);
                    TreeNode y = x.right;
                    while (y != null) {
                        s2.add(y);
                        y = y.left;
                    }
                }
            }
        }
        return ans;
    }
}
