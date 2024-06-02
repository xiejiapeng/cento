"""
给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
"""
class Solution(object):
    def rotate(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: None Do not return anything, modify nums in-place instead.
        """
        l = len(nums)
        k = k % l
        self.rotate2(nums, 0, l - k - 1)
        self.rotate2(nums, l - k, l - 1)
        self.rotate2(nums, 0, l - 1)

    def rotate2(self, nums, start, end):
        i = start
        j = end
        while i <= j:
            nums[i], nums[j] = nums[j], nums[i]
            i += 1
            j -= 1
