# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

"""
给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵
平衡
 二叉搜索树。
"""
class Solution(object):
    def sortedArrayToBST(self, nums):
        """
        :type nums: List[int]
        :rtype: TreeNode
        """
        return self.toBST(nums, 0, len(nums) - 1)

    def toBST(self, nums, start, end):
        if start > end:
            return None
        elif start == end:
            return TreeNode(val = nums[start])
        else:
            mid = (start + end) // 2
            root = TreeNode(val=nums[mid])
            root.left = self.toBST(nums, start, mid - 1)
            root.right = self.toBST(nums, mid + 1, end)
            return root

