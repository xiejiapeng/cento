package index.bfs;

import sulqn.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LCR153 {
    public List<List<Integer>> pathTarget(TreeNode root, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        LinkedList<Integer> l = new LinkedList<>();
        l.add(root.val);
        dfs(root, l, root.val, target, ans);
        return ans;
    }

    private void dfs(TreeNode node, LinkedList<Integer> path, int sum, int target, List<List<Integer>> ans) {
        if (node.left == null && node.right == null) {
            if (sum == target) {
                ans.add(new ArrayList<>(path));
            }
        } else {
            if (node.left != null) {
                path.addLast(node.left.val);
                dfs(node.left, path, sum + node.left.val, target, ans);
                path.removeLast();
            }

            if (node.right != null) {
                path.addLast(node.right.val);
                dfs(node.right, path, sum + node.right.val, target, ans);
                path.removeLast();
            }
        }
    }
}
