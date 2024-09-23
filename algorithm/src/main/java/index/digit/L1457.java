package index.digit;

import sulqn.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
给你一棵二叉树，每个节点的值为 1 到 9 。我们称二叉树中的一条路径是 「伪回文」的，当它满足：路径经过的所有节点值的排列中，存在一个回文序列。

请你返回从根到叶子节点的所有路径中 伪回文 路径的数目。
 */

public class L1457 {
    int ans = 0;

    public int pseudoPalindromicPaths(TreeNode root) {
        LinkedList<Integer> path = new LinkedList<>();
        path.addLast(root.val);
        dfs(root, path);
        return ans;
    }

    private void dfs(TreeNode cur, LinkedList<Integer> path) {
        if (cur.left == null && cur.right == null) {
            if (can(path)) {
                ans++;
            }
        } else {
            if (cur.left != null) {
                path.addLast(cur.left.val);
                dfs(cur.left, path);
                path.removeLast();
            }

            if (cur.right != null) {
                path.addLast(cur.right.val);
                dfs(cur.right, path);
                path.removeLast();
            }
        }
    }

    private boolean can(List<Integer> all) {
        System.out.println(all);
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : all) {
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
        }
        int c = 0;
        for (int x : cnt.keySet()) {
            if (cnt.get(x) % 2 != 0) {
                c++;
            }

        }
        if (c > 1) return false;
        System.out.println("true: " + all);
        return true;
    }
}
