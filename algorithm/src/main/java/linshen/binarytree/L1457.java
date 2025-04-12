package linshen.binarytree;

import sulqn.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;

/*
给你一棵二叉树，每个节点的值为 1 到 9 。我们称二叉树中的一条路径是 「伪回文」的，
当它满足：路径经过的所有节点值的排列中，存在一个回文序列。
请你返回从根到叶子节点的所有路径中 伪回文 路径的数目。
给定二叉树的节点数目在范围 [1, 10^5] 内
1 <= Node.val <= 9
 */

public class L1457 {
    int ans;
    public int pseudoPalindromicPaths (TreeNode root) {
        int[] a = new int[9];
        if(root.val == 0)return 0;
        else {
            a[root.val - 1]++;
            dfs(root, a);
        }
        return ans;
    }

    private void dfs(TreeNode root, int[] all) {
        if(root.left == null && root.right == null) {
            long cnt = Arrays.stream(all).filter(i -> i % 2 != 0).count();
            if(cnt <= 1)ans++;
        } else {
            if(root.left != null){
                all[root.left.val - 1]++;
                dfs(root.left, all);
                all[root.left.val - 1]--;
            }
            if(root.right != null){
                all[root.right.val - 1]++;
                dfs(root.right, all);
                all[root.right.val - 1]--;
            }
        }
    }
}
