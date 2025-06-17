package linshen.binarytree;

import sulqn.TreeNode;

/*
给你一棵二叉树的根节点 root ，二叉树中节点的值 互不相同 。另给你一个整数 start 。在第 0 分钟，感染 将会从值为 start 的节点开始爆发。

每分钟，如果节点满足以下全部条件，就会被感染：

节点此前还没有感染。
节点与一个已感染节点相邻。
返回感染整棵树需要的分钟数。
 */

public class L2385 {
    int ans = 0;
    TreeNode s = null;

    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(5);
        TreeNode c = new TreeNode(3);
        a.left = b;
        a.right = c;
        System.out.println(new L2385().amountOfTime(a, 3));
    }

    public int amountOfTime(TreeNode root, int start) {
        find(root, null, start);
        int h = height(s);
        return Math.max(h, ans) - 1;
    }

    private int height(TreeNode root) {
        if (root == null) return 0;
        else return 1 + Math.max(height(root.left), height(root.right));
    }

    private boolean find(TreeNode root, TreeNode parent, int start) {
        if (root == null) return false;
        if (root.val == start) {
            s = root;
            root.left = null;
            root.right = null;
            int h = height(root);
            ans = Math.max(ans, h);
            root.left = parent;
            return true;
        } else {
            if (find(root.left, root, start)) {
                root.left = parent;
                return true;
            } else if (find(root.right, root, start)) {
                root.right = parent;
                return true;
            } else {
                return false;
            }
        }
    }
}
