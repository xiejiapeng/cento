package linshen.binarytree;

import sulqn.TreeNode;

/*
给定一颗根结点为 root 的二叉树，树中的每一个结点都有一个 [0, 25] 范围内的值，
分别代表字母 'a' 到 'z'。
返回 按字典序最小 的字符串，该字符串从这棵树的一个叶结点开始，到根结点结束。
注：字符串中任何较短的前缀在 字典序上 都是 较小 的：
例如，在字典序上 "ab" 比 "aba" 要小。叶结点是指没有子结点的结点。
节点的叶节点是没有子节点的节点。
 */

public class L988 {
    String ans = "";
    public String smallestFromLeaf(TreeNode root) {
        char c = (char)('a' + root.val);
        dfs(root, "" + c);
        return ans;
    }

    private void dfs(TreeNode root, String sb) {
        if(root.left == null && root.right == null) {
            if(ans.isEmpty() || sb.compareTo(ans) < 0)ans = sb;
        } else {
            if(root.left != null) {
                char c = (char) ('a' + root.left.val);
                dfs(root.left, c + sb);
            }
            if(root.right != null) {
                char c = (char) ('a' + root.right.val);
                dfs(root.right, c + sb);
            }
        }
    }
}
