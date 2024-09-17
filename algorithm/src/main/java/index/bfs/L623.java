package index.bfs;

import sulqn.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/*
给定一个二叉树的根 root 和两个整数 val 和 depth ，在给定的深度 depth 处添加一个值为 val 的节点行。

注意，根节点 root 位于深度 1 。

加法规则如下:

给定整数 depth，对于深度为 depth - 1 的每个非空树节点 cur ，创建两个值为 val 的树节点作为 cur 的左子树根和右子树根。
cur 原来的左子树应该是新的左子树根的左子树。
cur 原来的右子树应该是新的右子树根的右子树。
如果 depth == 1 意味着 depth - 1 根本没有深度，那么创建一个树节点，值 val 作为整个原始树的新根，而原始树就是新根的左子树。
 */

public class L623 {
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode x = new TreeNode(val);
            x.left = root;
            return x;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int dep = 1;
        while (!queue.isEmpty()) {
            if (dep == depth - 1) {
                int size = queue.size();
                while (size-- > 0) {
                    TreeNode x = queue.poll();
                    System.out.println(x.val);
                    TreeNode left = x.left;
                    TreeNode right = x.right;
                    x.left = new TreeNode(val);
                    x.right = new TreeNode(val);
                    if (left != null) {
                        x.left.left = left;
                        queue.add(left);
                    }
                    if (right != null) {
                        x.right.right = right;
                        queue.add(right);
                    }

                }
            } else {
                int size = queue.size();
                while (size-- > 0) {
                    TreeNode x = queue.poll();
                    if (x.left != null) {
                        queue.add(x.left);
                    }
                    if (x.right != null) {
                        queue.add(x.right);
                    }
                }
            }
            dep++;
        }
        return root;
    }
}
