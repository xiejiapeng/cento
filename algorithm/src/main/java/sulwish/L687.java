package sulwish;

import sulqn.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*
给定一个二叉树的 root ，返回 最长的路径的长度 ，这个路径中的 每个节点具有相同值 。 这条路径可以经过也可以不经过根节点。

两个节点之间的路径长度 由它们之间的边数表示。
 */

public class L687 {
    Map<TreeNode, Integer> left = new HashMap<>();
    Map<TreeNode, Integer> right = new HashMap<>();
    int max = 1;

    public static void main(String[] args) {
        TreeNode r1 = new TreeNode(1);
        TreeNode r2 = new TreeNode(1);
        TreeNode r3 = new TreeNode(2);
        r1.left = r2;
        r1.right = r3;
        System.out.println(new L687().longestUnivaluePath(r1));
    }

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        return max - 1;
    }

    private void dfs(TreeNode cur) {
        int left = left(cur);
        int right = right(cur);

        if (left > 1 && right > 1) max = Math.max(max, left + right - 1);
        else max = Math.max(max, Math.max(left, right));

        if (cur.left != null) dfs(cur.left);
        if (cur.right != null) dfs(cur.right);
    }

    private int left(TreeNode r) {
        if (left.containsKey(r)) return left.get(r);
        else {
            int x;
            if (r.left == null || r.left.val != r.val) x = 1;
            else x = Math.max(left(r.left), right(r.left)) + 1;
            left.put(r, x);
            return x;
        }
    }

    private int right(TreeNode r) {
        if (right.containsKey(r)) return right.get(r);
        else {
            int x;
            if (r.right == null || r.right.val != r.val) x = 1;
            else x = Math.max(left(r.right), right(r.right)) + 1;
            right.put(r, x);
            return x;
        }

    }
}
