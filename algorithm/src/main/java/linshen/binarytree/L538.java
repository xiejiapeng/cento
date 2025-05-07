package linshen.binarytree;

import sulqn.TreeNode;

/*
给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。

提醒一下，二叉搜索树满足下列约束条件：

节点的左子树仅包含键 小于 节点键的节点。
节点的右子树仅包含键 大于 节点键的节点。
左右子树也必须是二叉搜索树。
 */

//todo h 重点掌握这道题，注意dfs中p的作用
public class L538 {
    public TreeNode convertBST(TreeNode root) {
        dfs(root, null);
        return root;
    }

    private void dfs(TreeNode root, TreeNode p) {
        if (root == null) return;
        if (root.right == null) {
            if (p != null) root.val += p.val;
        } else {
            dfs(root.right, p);
            TreeNode x = root.right;
            //todo h 注意，这里要不停往左找到最小的比root大的节点
            while (x.left != null) x = x.left;
            root.val += x.val;
        }
        dfs(root.left, root);
    }
}
