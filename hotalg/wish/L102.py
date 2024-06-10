# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

"""
给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
"""
class Solution(object):
    def levelOrder(self, root):
        """
        :type root: TreeNode
        :rtype: List[List[int]]
        """
        ans = []
        if root == None:
            return ans
        queue = []
        queue.append(root)
        while len(queue) > 0:
            k = len(queue)
            a = []
            for i in range(0,k):
                u = queue.pop(0)
                a.append(u.val)
                if u.left != None:
                    queue.append(u.left)
                if u.right != None:
                    queue.append(u.right)
            ans.append(a)
        return ans
