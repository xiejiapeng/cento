package sulqn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
给你一棵二叉树的根节点 root ，返回所有 重复的子树 。
对于同一类的重复子树，你只需要返回其中任意 一棵 的根结点即可。
如果两棵树具有 相同的结构 和 相同的结点值 ，则认为二者是 重复 的。
 */

public class L652 {
    Map<TreeNode, Integer> nodes = new HashMap<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);
        List<TreeNode> ans = new ArrayList<>();
        for (TreeNode node : nodes.keySet()) {
            if (nodes.get(node) > 1) ans.add(node);
        }
        return ans;
    }

    private void dfs(TreeNode cur) {
        boolean add = false;
        for (TreeNode node : nodes.keySet()) {
            if (same(node, cur)) {
                nodes.put(node, nodes.get(node) + 1);
                add = true;
            }
        }
        if (!add) nodes.put(cur, 1);
        if (cur.left != null) dfs(cur.left);
        if (cur.right != null) dfs(cur.right);
    }

    public boolean same(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        else if (a == null || b == null) return false;
        else {
            return same(a.left, b.left) && same(a.right, b.right) && (a.val == b.val);
        }
    }
}
