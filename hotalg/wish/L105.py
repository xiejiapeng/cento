# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
"""
给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。

"""
class Solution(object):
    def buildTree(self, preorder, inorder):
        """
        :type preorder: List[int]
        :type inorder: List[int]
        :rtype: TreeNode
        """
        return self.build(preorder, inorder, 0, len(preorder)-1,0,len(inorder)-1)

    def build(self, preo, ino, a, b, x, y):
        if a > b:
            return None
        elif a == b:
            return TreeNode(val=preo[a])
        else:
            for u in range(x, y+1):
                if ino[u] == preo[a]:
                    cnt = u - x
                    root = TreeNode(val=preo[a])
                    left = self.build(preo, ino, a+1, a+cnt, x, u-1)
                    right = self.build(preo, ino, a+cnt+1,b,u+1,y)
                    root.left = left
                    root.right = right
                    return root