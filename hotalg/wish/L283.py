"""
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

请注意 ，必须在不复制数组的情况下原地对数组进行操作。
"""
class Solution(object):
    def moveZeroes(self, nums):
        """
        :type nums: List[int]
        :rtype: None Do not return anything, modify nums in-place instead.
        """
        next = 0
        for n in nums:
            if n == 0:
                continue
            else:
                nums[next] = n
                next += 1
        nums[next:] = [0] * (len(nums) - next)