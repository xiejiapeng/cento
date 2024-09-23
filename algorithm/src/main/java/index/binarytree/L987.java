package index.binarytree;

import sulqn.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

/*
给你二叉树的根结点 root ，请你设计算法计算二叉树的 垂序遍历 序列。

对位于 (row, col) 的每个结点而言，其左右子结点分别位于 (row + 1, col - 1) 和 (row + 1, col + 1) 。树的根结点位于 (0, 0) 。

二叉树的 垂序遍历 从最左边的列开始直到最右边的列结束，按列索引每一列上的所有结点，形成一个按出现位置从上到下排序的有序列表。如果同行同列上有多个结点，则按结点的值从小到大进行排序。

返回二叉树的 垂序遍历 序列。
 */

public class L987 {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer, PriorityQueue<int[]>> all = new TreeMap<>();
        dfs(root, all, 0, 0);
        List<List<Integer>> ans = new ArrayList<>(all.size());

        all.navigableKeySet().iterator().forEachRemaining(col -> {
            System.out.println("col is " + col);
            PriorityQueue<int[]> q = all.get(col);
            List<Integer> a = new ArrayList<>();
            while (!q.isEmpty()) {
                a.add(q.poll()[1]);
            }
            ans.add(a);
        });
        return ans;
    }

    private void dfs(TreeNode root, TreeMap<Integer, PriorityQueue<int[]>> all, int row, int col) {
        if (root != null) {
            all.putIfAbsent(col, new PriorityQueue<>((o1, o2) -> {
                if (o1[0] == o2[0]) return Integer.compare(o1[1], o2[1]);
                else return Integer.compare(o1[0], o2[0]);
            }));
            all.get(col).add(new int[]{row, root.val});

            dfs(root.left, all, row + 1, col - 1);
            dfs(root.right, all, row + 1, col + 1);
        }
    }
}
