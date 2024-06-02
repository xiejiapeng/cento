"""
给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。

题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。

请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
"""
class Solution(object):
    def productExceptSelf(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        l = len(nums)
        prefix_mul = [1] * l
        suffix_mul = [1] * l


        for x, i in zip(nums, range(0, l)):
            if i == 0:
                prefix_mul[i] = 1
            else:
                prefix_mul[i] = prefix_mul[i-1] * nums[i-1]

        for x, i in zip(reversed(nums), range(l-1,-1,-1)):
            if i == l - 1:
                suffix_mul[i] = 1
            else:
                suffix_mul[i] = suffix_mul[i+1] * nums[i+1]

        ans = []

        print(prefix_mul)
        print(suffix_mul)

        for x, y in zip(prefix_mul, suffix_mul):
            ans.append(x * y)
        return ans

if __name__ == '__main__':
    print(Solution().productExceptSelf([1,2,3,4]))