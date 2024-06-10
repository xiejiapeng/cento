# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
"""
给你一个二叉树的根节点 root ， 检查它是否轴对称。
"""
class Solution(object):
    def isSymmetric(self, root):
        """
        :type root: TreeNode
        :rtype: bool
        """
        return self.same(self.flip(root.left), root.right)

    def same(self, left, right):
        if left == None and right == None:
            return True
        elif left == None or right == None:
            return False
        else:
            return left.val == right.val and self.same(left.left, right.left) and self.same(left.right, right.right)

    def flip(self, root):
        if root == None:
            return None
        else:
            root.left, root.right = self.flip(root.right), self.flip(root.left)