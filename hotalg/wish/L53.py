"""
给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

子数组
是数组中的一个连续部分。
"""
class Solution(object):
    def maxSubArray(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        l = len(nums)
        sum = [0] * (l + 1)
        minnum = 0
        maxans = nums[0]
        for x, i in zip(nums, range(0, l)):
            sum[i+1] = sum[i] + x
            maxans = max(maxans, sum[i+1] - minnum)
            minnum = min(minnum, sum[i+1])
        return maxans