package index.binarytree;

import sulqn.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

/*
给你一棵二叉树的根节点 root ，返回所有 重复的子树 。

对于同一类的重复子树，你只需要返回其中任意 一棵 的根结点即可。

如果两棵树具有 相同的结构 和 相同的结点值 ，则认为二者是 重复 的。
 */

public class L652 {
    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        TreeNode d = new TreeNode(4);
        TreeNode e = new TreeNode(2);
        TreeNode f = new TreeNode(4);
        TreeNode g = new TreeNode(4);
        a.left = b;
        a.right = c;

        b.left = d;
        c.left = e;
        c.right = f;

        e.left = g;

        System.out.println(new L652().findDuplicateSubtrees(a));
    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> leaves = leaves(root);
        Map<TreeNode, TreeNode> lp = leftParent(root);
        Map<TreeNode, TreeNode> rp = rightParent(root);
        return dfs(leaves, lp, rp);
    }

    private List<TreeNode> dfs(List<TreeNode> l, Map<TreeNode, TreeNode> lp, Map<TreeNode, TreeNode> rp) {
        List<TreeNode> ans = new ArrayList<>();
        if (l.size() <= 1) return ans;
        Map<Integer, List<TreeNode>> ds = divide(l);
        for (List<TreeNode> value : ds.values()) {
            if (value.size() <= 1) continue;
            ans.add(value.get(0));
        }
        ds.values().forEach(t -> {
            Set<TreeNode> x = t.stream().map(n -> lp.get(n)).filter(z -> z != null).collect(Collectors.toSet());
            List<TreeNode> y = dfs(new ArrayList<>(x), lp, rp);
            ans.addAll(y);

            Set<TreeNode> xx = t.stream().map(n -> rp.get(n)).filter(z -> z != null).collect(Collectors.toSet());
            List<TreeNode> yy = dfs(new ArrayList<>(xx), lp, rp);
            ans.addAll(yy);
        });
        return ans;

    }

    public Map<TreeNode, TreeNode> leftParent(TreeNode root) {
        if (root == null) {
            return new HashMap<>();
        }
        Map<TreeNode, TreeNode> ans = new HashMap<>();
        Map<TreeNode, TreeNode> l = leftParent(root.left);
        Map<TreeNode, TreeNode> r = leftParent(root.right);
        if (root.right != null) ans.put(root.right, root);
        ans.putAll(l);
        ans.putAll(r);
        return ans;
    }

    public Map<TreeNode, TreeNode> rightParent(TreeNode root) {
        if (root == null) {
            return new HashMap<>();
        }
        Map<TreeNode, TreeNode> ans = new HashMap<>();
        Map<TreeNode, TreeNode> l = rightParent(root.left);
        Map<TreeNode, TreeNode> r = rightParent(root.right);
        if (root.left != null) ans.put(root.left, root);
        ans.putAll(l);
        ans.putAll(r);
        return ans;
    }

    public List<TreeNode> leaves(TreeNode root) {
        if (root.left == null && root.right == null) {
            return Arrays.asList(root);
        } else if (root.left == null) {
            return leaves(root.right);
        } else if (root.right == null) {
            return leaves(root.left);
        } else {
            List<TreeNode> ans = new ArrayList<>();
            ans.addAll(leaves(root.left));
            ans.addAll(leaves(root.right));
            return ans;
        }
    }

    public Map<Integer, List<TreeNode>> divide(List<TreeNode> a) {
        Map<Integer, List<TreeNode>> ans = new HashMap<>();
        for (TreeNode x : a) {
            ans.putIfAbsent(x.val, new ArrayList<>());
            ans.get(x.val).add(x);
        }
        return ans;
    }
}
