package sulwish;

import sulqn.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

/*
给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 k 。

返回到目标结点 target 距离为 k 的所有结点的值的列表。 答案可以以 任何顺序 返回。
 */

public class L863 {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<TreeNode, Set<TreeNode>> neighbour = new HashMap<>();
        dfs(root, neighbour);
        for (TreeNode x : neighbour.keySet()) {
            System.out.println("key is " + x.val + ", values are " + neighbour.get(x).stream().map(t -> t.val).collect(Collectors.toList()));
        }
        List<Integer> ans = new ArrayList<>();
        Set<TreeNode> visited = new HashSet<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(target);
        visited.add(target);
        int dist = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode x = queue.poll();
                System.out.println(x.val);
                if (dist == k) {
                    ans.add(x.val);
                }
                for (TreeNode nx : neighbour.get(x)) {
                    if (!visited.contains(nx)) {
                        visited.add(nx);
                        queue.add(nx);
                    }
                }
            }
            dist++;
        }
        return ans;
    }

    private void dfs(TreeNode root, Map<TreeNode, Set<TreeNode>> neighbour) {
        neighbour.putIfAbsent(root, new HashSet<>());
        if (root.left != null) {
            TreeNode left = root.left;
            neighbour.get(root).add(left);
            neighbour.putIfAbsent(left, new HashSet<>());
            neighbour.get(left).add(root);

            dfs(root.left, neighbour);
        }

        if (root.right != null) {
            TreeNode right = root.right;
            neighbour.get(root).add(right);
            neighbour.putIfAbsent(right, new HashSet<>());
            neighbour.get(right).add(root);

            dfs(root.right, neighbour);
        }
    }
}
