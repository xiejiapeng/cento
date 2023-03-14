package solutions;

import java.util.Stack;

public class L230 {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while(root != null) {
            stack.add(root);
            root = root.left;
        }

        while (!stack.isEmpty()) {
            TreeNode p = stack.pop();
            k--;
            if(k == 0)return p.val;

            if(p.right != null){
                TreeNode q = p.right;
                while (q != null){
                    stack.add(q);
                    q = q.left;
                }
            }

        }
        return -1;
    }
}
