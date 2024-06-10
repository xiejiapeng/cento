# Definition for a binary tree node.
class TreeNode(object):
     def __init__(self, val=0, left=None, right=None):
         self.val = val
         self.left = left
         self.right = right

"""
二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。
该路径 至少包含一个 节点，且不一定经过根节点。

路径和 是路径中各节点值的总和。

给你一个二叉树的根节点 root ，返回其 最大路径和 。
"""
class Solution(object):
    def maxPathSum(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        self.ans = root.val
        x = self.left(root)
        y = self.right(root)
        self.ans = max(self.ans, max(0, x)+max(0, y) + root.val)
        return self.ans

    def left(self, root):
        if root.left == None:
            return 0
        else:
            x = self.left(root.left)
            y = self.right(root.left)
            self.ans = max(self.ans, max(0,x)+max(0,y)+root.left.val)
            return max(0, max(root.left.val, max(root.left.val + x, root.left.val + y)))

    def right(self, root):
        if root.right == None:
            return 0
        else:
            x = self.left(root.right)
            y = self.right(root.right)
            self.ans = max(self.ans, max(0,x)+max(0,y)+root.right.val)
            return max(0, max(root.right.val, max(root.right.val + x, root.right.val + y)))

if __name__ == '__main__':
    a = TreeNode(1)
    b = TreeNode(2)
    c = TreeNode(3)
    a.left = b
    a.right = c
    print(Solution().maxPathSum(a))