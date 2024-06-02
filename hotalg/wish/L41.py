"""
给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。

请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
"""
class Solution(object):
    def firstMissingPositive(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        l = len(nums)
        for i in range(0,l):
            x = nums[i]
            print("{} is, x is {}, i is {}".format(nums,x,i))
            if x == i + 1:
                continue
            else:
                while x-1 >= 0 and x-1 < l and nums[x-1] != x and (x - 1 != i):
                    tmp = nums[x-1]
                    nums[x-1] = x
                    x = tmp
                print("x={},i={}".format(x,i))
                nums[i] = x
        print(nums)
        for x, i in zip(nums, range(0,l)):
            if x != i + 1:
                return i + 1

        return l+1
