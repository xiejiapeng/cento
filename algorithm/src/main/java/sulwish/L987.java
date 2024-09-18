package sulwish;

import sulqn.TreeNode;

import java.util.*;

public class L987 {
    // row col val
    PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            if (o1[1] != o2[1]) return Integer.compare(o1[1], o2[1]);
            else if (o1[0] != o2[0]) return Integer.compare(o1[0], o2[0]);
            else return Integer.compare(o1[2], o2[2]);
        }
    });

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        dfs(root, 0, 0);
        LinkedList<List<Integer>> ans = new LinkedList<>();
        int lastCol = -1;
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            if (ans.isEmpty()) {
                List<Integer> l = new ArrayList<>();
                l.add(p[2]);
                lastCol = p[1];
                ans.addLast(l);
            } else {
                if (p[1] == lastCol) {
                    List<Integer> l = ans.getLast();
                    l.add(p[2]);
                } else {
                    List<Integer> l = new ArrayList<>();
                    l.add(p[2]);
                    lastCol = p[1];
                    ans.addLast(l);
                }
            }
        }
        return ans;
    }

    private void dfs(TreeNode node, int row, int col) {
        queue.add(new int[]{row, col, node.val});
        if (node.left != null) dfs(node.left, row + 1, col - 1);
        if (node.right != null) dfs(node.right, row + 1, col + 1);
    }
}
