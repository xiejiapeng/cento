# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

"""
给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
"""
class Solution(object):
    def kthSmallest(self, root, k):
        """
        :type root: TreeNode
        :type k: int
        :rtype: int
        """
        self.k = k
        self.dfs(root)
        return self.ans

    def dfs(self, root):
        if root.left != None:
            self.dfs(root.left)

        self.k = self.k - 1
        if self.k == 0:
            self.ans = root.val

        if root.right != None:
            self.dfs(root.right)