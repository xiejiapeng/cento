package linshen.binarytree;

import sulqn.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 */

public class L515 {
    public List<Integer> largestValues(TreeNode root) {
        Queue<TreeNode> all = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();
        if(root == null)return ans;
        else {
            all.add(root);
            while (!all.isEmpty()) {
                int n = all.size();
                int m = Integer.MIN_VALUE;
                while (n-- > 0) {
                    TreeNode x = all.poll();
                    m = Math.max(m, x.val);
                    if(x.left != null)all.add(x.left);
                    if(x.right != null)all.add(x.right);
                }
                ans.add(m);
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        TreeNode x = new TreeNode(1);
        System.out.println(new L515().largestValues(x));
    }
}
