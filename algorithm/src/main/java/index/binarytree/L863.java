package index.binarytree;

import sulqn.TreeNode;

import java.util.*;

/*
给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 k ，返回到目标结点 target 距离为 k 的所有结点的值的数组。
 */

public class L863 {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<Integer, List<Integer>> neighbors = new HashMap<>();
        dfs(root, neighbors);
        System.out.println(neighbors);
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        visited.add(target.val);
        queue.add(target.val);
        int dist = 0;
        List<Integer> ans = new ArrayList<>();
        boolean find = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (dist == k) find = true;
            while (size-- > 0) {
                int x = queue.poll();
                if (dist == k) {
                    ans.add(x);
                }
                if (neighbors.containsKey(x)) {
                    for (int y : neighbors.get(x)) {
                        if (!visited.contains(y)) {
                            visited.add(y);
                            queue.add(y);
                        }
                    }
                }

            }
            dist++;
            if (find) break;
        }
        return ans;
    }

    private void dfs(TreeNode root, Map<Integer, List<Integer>> neighbors) {
        if (root.left != null) {
            neighbors.putIfAbsent(root.val, new ArrayList<>());
            neighbors.putIfAbsent(root.left.val, new ArrayList<>());

            neighbors.get(root.val).add(root.left.val);
            neighbors.get(root.left.val).add(root.val);
            dfs(root.left, neighbors);
        }
        if (root.right != null) {
            neighbors.putIfAbsent(root.val, new ArrayList<>());
            neighbors.putIfAbsent(root.right.val, new ArrayList<>());

            neighbors.get(root.val).add(root.right.val);
            neighbors.get(root.right.val).add(root.val);
            dfs(root.right, neighbors);
        }
    }
}
