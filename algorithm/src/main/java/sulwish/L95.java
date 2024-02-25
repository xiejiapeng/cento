package sulwish;

import sulqn.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
 */

public class L95 {
    List<TreeNode> ans = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(new L95().generateTrees(3));
    }

    public List<TreeNode> generateTrees(int n) {
        dfs(n, new HashSet<>(), null);
        return ans;
    }

    public void dfs(int n, Set<Integer> used, TreeNode cur) {
        if (used.size() == n) {
            boolean add = true;
            TreeNode clo = clone(cur);
            for (TreeNode t : ans) {
                if (same(t, clo)) {
                    add = false;
                    break;
                }
            }
            if (add) ans.add(clone(cur));
            return;
        } else {
            for (int i = 1; i <= n; i++) {
                if (used.contains(i)) continue;
                else {
                    used.add(i);
                    cur = insert(cur, i);
                    dfs(n, used, cur);
                    cur = remove(cur, i);
                    used.remove(i);
                }
            }
        }
    }

    public TreeNode insert(TreeNode cur, int x) {
        if (cur == null) {
            cur = new TreeNode(x);
        } else {
            if (x > cur.val) {
                cur.right = insert(cur.right, x);
            } else {
                cur.left = insert(cur.left, x);
            }
        }
        return cur;
    }

    public TreeNode remove(TreeNode cur, int x) {
        if (cur.val == x) {
            cur = null;
        } else {
            if (x > cur.val) {
                cur.right = remove(cur.right, x);
            } else {
                cur.left = remove(cur.left, x);
            }
        }
        return cur;
    }

    public TreeNode clone(TreeNode node) {
        if (node == null) return null;
        else {
            TreeNode x = new TreeNode(node.val);
            x.left = clone(node.left);
            x.right = clone(node.right);
            return x;
        }
    }

    public boolean same(TreeNode a, TreeNode b) {
        if (a == null && b != null) return false;
        if (a != null && b == null) return false;
        if (a == null && b == null) return true;
        if (a.val != b.val) return false;
        else return same(a.left, b.left) && same(a.right, b.right);

    }
}
