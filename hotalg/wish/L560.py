"""
给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。

子数组是数组中元素的连续非空序列。
"""
class Solution(object):
    def subarraySum(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: int
        """
        l = len(nums)
        sum = [0] * (l + 1)
        all = {}
        all[0] = 1
        ans = 0
        for x, i in zip(nums, range(0, l)):
            sum[i+1] = x + sum[i]

            target = sum[i+1] - k
            ans += all.get(target, 0)

            all[sum[i+1]] = all.get(sum[i+1], 0) + 1

        return ans


if __name__ == '__main__':
    solu = Solution()
    print(solu.subarraySum([1,1,1], 2))
