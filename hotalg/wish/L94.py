# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

"""
给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。

"""
class Solution(object):
    def inorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        ans = []
        self.traverse(root, ans)
        return ans

    def traverse(self, root, ans):
        if root == None:
            return
        else:
            self.traverse(root.left, ans)
            ans.append(root.val)
            self.traverse(root.right, ans)
