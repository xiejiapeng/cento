"""
给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。

判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
"""
class Solution(object):
    def canJump(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        limit = min(nums[0], len(nums)-1)
        for x, i in zip(nums, range(0, len(nums))):
            if i > limit:
                break
            limit = max(limit, min(i + x, len(nums)-1))
            print("limit {}".format(limit))

        return limit == len(nums) - 1