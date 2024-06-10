# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
"""
给你一棵二叉树的根节点，返回该树的 直径 。

二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。

两节点之间路径的 长度 由它们之间边数表示。
"""
class Solution(object):
    ans = 0
    def diameterOfBinaryTree(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        self.traverse(root)
        return self.ans

    def traverse(self, root):
        if root == None:
            return 0
        else:
            if root.left == None and root.right == None:
                return 0
            elif root.left == None:
                x = 1 + self.traverse(root.right)
                self.ans = max(self.ans, x)
                return x
            elif root.right == None:
                x = 1 + self.traverse(root.left)
                self.ans = max(self.ans, x)
                return x
            else:
                x = self.traverse(root.left)
                y = self.traverse(root.right)
                self.ans = max(self.ans, 2 + x + y)

                return 1 + max(x,y)