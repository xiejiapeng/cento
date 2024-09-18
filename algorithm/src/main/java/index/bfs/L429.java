package index.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class L429 {
    public List<List<Integer>> levelOrder(Node root) {
        Queue<Node> queue = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();
        if (root != null) {
            queue.add(root);
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> tmp = new ArrayList<>();
            while (size-- > 0) {
                Node x = queue.poll();
                tmp.add(x.val);
                if (!x.children.isEmpty()) {
                    List<Node> cs = x.children;
                    for (Node c : cs) {
                        queue.add(c);
                    }
                }
            }
            ans.add(tmp);
        }
        return ans;
    }
}
