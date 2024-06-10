# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
"""
给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。

有效 二叉搜索树定义如下：

节点的左
子树
只包含 小于 当前节点的数。
节点的右子树只包含 大于 当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。
"""
class Solution(object):
    def isValidBST(self, root):
        """
        :type root: TreeNode
        :rtype: bool
        """
        return self.dfs(root, -2 ** 31 * 2 - 1, 2 ** 31)

    def dfs(self, root, low, upper):
        if root.val <= low or root.val >= upper:
            return False
        else:
            left = root.left == None or self.dfs(root.left, low, root.val)
            right = root.right == None or self.dfs(root.right, root.val, upper)
            return left and right