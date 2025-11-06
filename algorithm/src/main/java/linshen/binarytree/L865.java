package linshen.binarytree;

import sulqn.TreeNode;

/*
给定一个根为 root 的二叉树，每个节点的深度是 该节点到根的最短距离 。
返回包含原始树中所有 最深节点 的 最小子树 。
如果一个节点在 整个树 的任意节点之间具有最大的深度，则该节点是 最深的 。
一个节点的 子树 是该节点加上它的所有后代的集合。
提示：

树中节点的数量在 [1, 500] 范围内。
0 <= Node.val <= 500
每个节点的值都是 独一无二 的。
   0
1    3
 2
 */

public class L865 {
    int max = 0;
    int ah = -1;
    TreeNode ans = null;
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        int h = height(root);
        dfs(root, 1, h);
        return ans;
    }

    //root包含最深子节点的个数
    private int dfs(TreeNode root, int h, int height) {
        if(root == null)return 0;
        int a;
        if(h == height)a = 1;
        else {
            int x = dfs(root.left, h + 1, height);
            int y = dfs(root.right, h + 1, height);
            a = x + y;
        }
        if(a > max) {
            max = a;
            ans = root;
            ah = h;
        } else if(a == max) {
            //todo 注意，条件是>
            if(h > ah) {
                ah = h;
                ans = root;
            }
        }
        return a;
    }

    private int height(TreeNode root) {
        if(root == null)return 0;
        if(root.left == null && root.right == null)return 1;
        else return 1 + Math.max(height(root.left), height(root.right));
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(0);
        TreeNode b = new TreeNode(1);
        TreeNode c = new TreeNode(3);
        TreeNode d = new TreeNode(2);
        a.left = b;
        a.right = c;
        b.right = d;
        TreeNode y = new L865().subtreeWithAllDeepest(a);
        System.out.println(y.val);
    }
}
