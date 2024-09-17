package index.dfs;

import sulqn.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同
的不同 二叉搜索树 。可以按 任意顺序 返回答案。
 */

public class L95 {
    public static void main(String[] args) {
        System.out.println(new L95().generateTrees(3));
    }

    public List<TreeNode> generateTrees(int n) {
        Set<TreeNode> ans = new HashSet<>();

        dfs(n, ans, null, new HashSet<>());
        return new ArrayList<>(ans);
    }

    private void dfs(int n, Set<TreeNode> ans, TreeNode cur, Set<Integer> used) {
        if (used.size() == n) {
            add(ans, cur);
        } else {
            for (int i = 1; i <= n; i++) {
                if (!used.contains(i)) {
                    used.add(i);
                    TreeNode y = null;
                    if (cur == null) {
                        y = new TreeNode(i);
                    } else {
                        y = clone(cur);
                        insert(y, i);
                    }
                    dfs(n, ans, y, used);
                    used.remove(i);
                }
            }
        }
    }

    private TreeNode clone(TreeNode node) {
        if (node == null) return null;
        else {
            TreeNode x = new TreeNode(node.val);
            x.left = clone(node.left);
            x.right = clone(node.right);
            return x;
        }
    }

    private void insert(TreeNode root, int x) {
        if (root.val < x) {
            if (root.right == null) {
                root.right = new TreeNode(x);
            } else {
                insert(root.right, x);
            }
        } else {
            if (root.left == null) {
                root.left = new TreeNode(x);
            } else {
                insert(root.left, x);
            }
        }
    }

    private boolean same(TreeNode x, TreeNode y) {
        if (x == null && y == null) return true;
        else if (x == null || y == null) return false;
        else {
            return x.val == y.val && same(x.left, y.left) && same(x.right, y.right);
        }
    }

    private void add(Set<TreeNode> all, TreeNode cur) {
        for (TreeNode x : all) {
            if (same(x, cur)) return;
        }
        all.add(cur);
    }
}
