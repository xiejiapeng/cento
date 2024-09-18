package sulwish;

import sulqn.TreeNode;

import java.util.Arrays;

/*
给你一棵二叉树，每个节点的值为 1 到 9 。我们称二叉树中的一条路径是 「伪回文」的，当它满足：路径经过的所有节点值的排列中，存在一个回文序列。

请你返回从根到叶子节点的所有路径中 伪回文 路径的数目。
 */

public class L1457 {
    int ans = 0;

    public int pseudoPalindromicPaths(TreeNode root) {
        int[] cur = new int[10];
        cur[root.val]++;
        dfs(root, cur);
        return ans;
    }

    private void dfs(TreeNode node, int[] cur) {
        if (node.left == null && node.right == null) {
            if (pp(cur)) ans++;
        } else {
            if (node.left != null) {
                cur[node.left.val]++;
                dfs(node.left, cur);
                cur[node.left.val]--;
            }
            if (node.right != null) {
                cur[node.right.val]++;
                dfs(node.right, cur);
                cur[node.right.val]--;
            }
        }
    }

    public boolean pp(int[] cur) {
        System.out.println(Arrays.toString(cur));
        boolean find = false;
        for (int i = 0; i < cur.length; i++) {
            if (cur[i] % 2 == 0) continue;
            else {
                if (find) return false;
                else find = true;
            }
        }
        return true;
    }
}
