package sulwish;

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
        return construct(nums, 0, nums.length - 1);
    }

    public TreeNode construct(int[] nums, int x, int y) {
        if (x > y) {
            return null;
        } else if (x == y) {
            TreeNode r = new TreeNode(nums[x]);
            return r;
        } else {
            int max = nums[x];
            int maxId = x;
            for (int i = x; i <= y; i++) {
                if (nums[i] >= max) {
                    max = nums[i];
                    maxId = i;
                }
            }

            TreeNode left = construct(nums, x, maxId - 1);
            TreeNode right = construct(nums, maxId + 1, y);
            TreeNode r = new TreeNode(max);
            r.left = left;
            r.right = right;
            return r;

        }
    }
}
