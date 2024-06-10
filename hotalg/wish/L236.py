# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

"""
给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
"""


class Solution(object):
    def lowestCommonAncestor(self, root, p, q):
        """
        :type root: TreeNode
        :type p: TreeNode
        :type q: TreeNode
        :rtype: TreeNode
        """
        return self.dfs(root, p, q)

    def dfs(self, root, p, q):
        """
        p if only contains p
        q if only contains q
        null if don't contain p or q
        x if contain p and q
        :param root:
        :param p:
        :param q:
        :return:
        """
        if root == None:
            return None
        if root == p:
            return p
        elif root == q:
            return q
        else:
            l = self.dfs(root.left, p, q)
            r = self.dfs(root.right, p, q)
            if l == None and r == None:
                return None
            elif l == None:
                return r
            elif r == None:
                return l
            else:
                return root

