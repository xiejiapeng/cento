package index.bfs;

import sulqn.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class L513 {
    public int findBottomLeftValue(TreeNode root) {
        int ans = -1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean first = true;
            while (size-- > 0) {
                TreeNode x = queue.poll();
                if (first) ans = x.val;
                first = false;
                if (x.left != null) queue.add(x.left);
                if (x.right != null) queue.add(x.right);
            }
        }
        return ans;
    }
}
