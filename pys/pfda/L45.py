"""
给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。

每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:

0 <= j <= nums[i]
i + j < n
返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
"""
class Solution(object):
    def jump(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums) == 1:
            return 0
        limit = min(nums[0], len(nums)-1)
        step = 0
        pos = 0
        while limit < len(nums)-1:
            step += 1
            l = limit
            while pos <= limit:
                l = max(l, pos + nums[pos])
                pos += 1
            limit = l

        return step + 1

if __name__ == '__main__':
    print(Solution().jump([1,2,3]))