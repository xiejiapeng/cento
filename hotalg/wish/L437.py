# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
"""
给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。

路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。


"""
class Solution(object):
    def pathSum(self, root, targetSum):
        """
        :type root: TreeNode
        :type targetSum: int
        :rtype: int
        """
        dummy = TreeNode(0)
        dummy.left = root
        self.ans = 0
        self.dfs({}, dummy, 0, targetSum)
        return self.ans

    def dfs(self, pathSum, node, l, targetSum):
        target = l - targetSum
        self.ans += pathSum.get(target, 0)
        pathSum[l+node.val] = pathSum.get(l+node.val, 0) + 1
        if node.left != None:
            self.dfs(pathSum, node.left, l + node.left.val, targetSum)
        if node.right != None:
            self.dfs(pathSum, node.right, 1 + node.right.val, targetSum)
        pathSum[l + node.val] = pathSum.get(l + node.val, 0) + 1