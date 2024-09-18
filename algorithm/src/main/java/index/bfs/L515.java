package index.bfs;

import sulqn.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class L515 {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int max = Integer.MIN_VALUE;
            int size = queue.size();
            while (size-- > 0) {
                TreeNode x = queue.poll();
                max = Math.max(max, x.val);
                if (x.left != null) queue.add(x.left);
                if (x.right != null) queue.add(x.right);
            }
            ans.add(max);
        }
        return ans;
    }
}
