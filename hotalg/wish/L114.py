# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

"""
给你二叉树的根结点 root ，请你将它展开为一个单链表：

展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
展开后的单链表应该与二叉树 先序遍历 顺序相同。

"""
class Solution(object):
    def flatten(self, root):
        """
        :type root: TreeNode
        :rtype: None Do not return anything, modify root in-place instead.
        """
        self.trans(root)
        return root

    def trans(self, root):
        """
        return the tail
        :param root:
        :return:
        """
        if root == None:
            return None
        if root.left == None and root.right == None:
            return root
        elif root.left == None:
            return self.trans(root.right)
        elif root.right == None:
            left = root.left
            root.left = None
            root.right = left
            return self.trans(root.right)
        else:
            left = root.left
            right = root.right
            lt = self.trans(left)
            rt = self.trans(right)
            root.left = None
            root.right = left
            lt.right = right
            return rt