package index.dfs;

import sulqn.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class L662 {
    public int widthOfBinaryTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        long ansW = 1;
        Map<TreeNode, Long> column = new HashMap<>();
        queue.add(root);
        column.put(root, 0l);
        while (!queue.isEmpty()) {
            int size = queue.size();
            long min = Long.MAX_VALUE;
            long max = Long.MIN_VALUE;
            boolean has = false;
            while (size-- > 0) {
                TreeNode x = queue.poll();
                long col = column.get(x);
                if (x.left != null) {
                    has = true;
                    queue.add(x.left);
                    column.put(x.left, col * 2);
                    min = Math.min(min, col * 2);
                    max = Math.max(max, col * 2);
                }
                if (x.right != null) {
                    has = true;
                    queue.add(x.right);
                    column.put(x.right, col * 2 + 1);
                    min = Math.min(min, col * 2 + 1);
                    max = Math.max(max, col * 2 + 1);
                }
            }
            if (has && max - min + 1 > ansW) {
                ansW = max - min + 1;
            }
        }
        return (int) ansW;
    }
}
