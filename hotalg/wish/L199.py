# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
"""
给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
"""


class Solution(object):
    def rightSideView(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        ans = []
        if root == None:
            return ans

        queue = []
        queue.append(root)
        while len(queue) > 0:
            l = len(queue)
            for i in range(0, l):
                x = queue.pop(0)
                if x.left != None:
                    queue.append(x.left)
                if x.right != None:
                    queue.append(x.right)
                a = x.val
            ans.append(a)
        return ans
