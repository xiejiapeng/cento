"""
给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
同时还满足 nums[i] + nums[j] + nums[k] == 0 。请

你返回所有和为 0 且不重复的三元组。

注意：答案中不可以包含重复的三元组。
"""
class Solution(object):
    def threeSum(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        nums = sorted(nums)
        ans = set()
        n = len(nums)
        for x, i in zip(nums, range(0, n)):
            l = i + 1
            r = n - 1
            while l < r:
                if x + nums[l] + nums[r] < 0:
                    l += 1
                elif x + nums[l] + nums[r] > 0:
                    r -= 1
                else:
                    ans.add((x, nums[l], nums[r]))
                    l += 1

        return list([list(t) for t in ans])
