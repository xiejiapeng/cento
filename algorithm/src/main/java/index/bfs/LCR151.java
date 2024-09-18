package index.bfs;

import sulqn.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LCR151 {
    public List<List<Integer>> decorateRecord(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        queue.add(root);
        boolean fromLeft = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> a = new ArrayList<>();
            while (size-- > 0) {
                if (fromLeft) {
                    TreeNode x = queue.pollFirst();
                    a.add(x.val);
                    if (x.left != null) queue.addLast(x.left);
                    if (x.right != null) queue.addLast(x.right);
                } else {
                    TreeNode x = queue.pollLast();
                    a.add(x.val);
                    if (x.right != null) queue.addFirst(x.right);
                    if (x.left != null) queue.addFirst(x.left);
                }
            }
            fromLeft = !fromLeft;
            ans.add(a);
        }
        return ans;
    }
}
