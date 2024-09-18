package sulwish;

import sulqn.TreeNode;

/*
给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，使得所有节点的值在[low, high]中。修剪树 不应该 改变保留在树中的元素的相对结构 (即，如果没有被移除，原有的父代子代关系都应当保留)。 可以证明，存在 唯一的答案 。

所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。
 */

public class L669 {
    TreeNode left; //left is the last tree node whose left node is less than low
    TreeNode right; //right is the last tree node whose right node is larger than high
    public TreeNode trimBST(TreeNode root, int low, int high) {
        TreeNode dummy = new TreeNode(-1);
        dummy.left = root;
        findLeft(root, low, high);
        if(left != null && left.left != null) {
            left.left = left.left.right;
        }

        dummy.left = null;
        dummy.right = root;

        findRight(root, dummy, low, high);


        if(right != null && right.right != null){
            right.right = right.right.left;
        }

        return dummy.right;

    }

    private void findLeft(TreeNode cur, int low, int high) {
        if(cur.left != null) {
            findLeft(cur.left, low, high);
        }
        if(cur.left != null && cur.left.val < low){
            left = cur;
        }
        if(cur.right != null) {
            findLeft(cur.right, low, high);
        }
    }

    private void findRight(TreeNode cur, TreeNode prev, int low, int high) {
        if(cur.right != null) {
            findRight(cur.right, cur, low, high);
        }

        if(cur.val > high){
            right = prev;
        }

        if(cur.left != null) {
            findRight(cur.left, cur, low, high);
        }
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode(1);
        TreeNode s = new TreeNode(2);
        t.right = s;
        System.out.println(new L669().trimBST(t, 2, 4));
    }
}
