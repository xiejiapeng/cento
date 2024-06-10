"""
给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

返回 滑动窗口中的最大值 。
"""
import heapq
class Solution(object):
    def maxSlidingWindow(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: List[int]
        """
        tmp = []
        ans = []
        l = len(nums)
        for x, i in zip(nums, range(0, l+k)):
            while len(tmp) > 0 and nums[tmp[-1]] <= x:
                tmp.pop(-1)
            tmp.append(i)
            if i >= k:
                to_pop = i - k
                if tmp[0] == to_pop:
                    tmp.pop(0)

            if i >= k - 1:
                ans.append(nums[tmp[0]])

        return ans


if __name__ == '__main__':
    solu = Solution()
    print(solu.maxSlidingWindow([1,3,-1,-3,5,3,6,7], 3))



