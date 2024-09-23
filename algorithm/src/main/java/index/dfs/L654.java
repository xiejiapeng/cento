package index.dfs;

import sulqn.TreeNode;

/*
给定一个不重复的整数数组 nums 。 最大二叉树 可以用下面的算法从 nums 递归地构建:

创建一个根节点，其值为 nums 中的最大值。
递归地在最大值 左边 的 子数组前缀上 构建左子树。
递归地在最大值 右边 的 子数组后缀上 构建右子树。
返回 nums 构建的 最大二叉树 。
 */

public class L654 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        int n = nums.length;
        int[][] max = new int[n][n];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    max[i][j] = i;
                } else {
                    int t = max[i + 1][j];
                    if (nums[i] > nums[t]) max[i][j] = i;
                    else max[i][j] = t;
                }
            }
        }
        return construct(nums, max, 0, n - 1);
    }

    public TreeNode construct(int[] nums, int[][] max, int left, int right) {
        if (left > right) {
            return null;
        } else if (left == right) {
            TreeNode x = new TreeNode(nums[left]);
            return x;
        } else {
            int t = max[left][right];
            TreeNode x = new TreeNode(nums[t]);
            TreeNode l = construct(nums, max, left, t - 1);
            TreeNode r = construct(nums, max, t + 1, right);
            x.left = l;
            x.right = r;
            return x;
        }
    }
}
