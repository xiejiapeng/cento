"""
给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的
子集（幂集）。

解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
"""
class Solution(object):
    def subsets(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        nums = sorted(nums)
        self.ans = set()
        self.dfs(nums, 0, list())
        return [list(x) for x in self.ans]

    def dfs(self, nums, id, cur):
        if id == len(nums):
            self.ans.append(tuple(list(cur)))
        else:
            self.dfs(nums, id + 1, cur)

            cur.append(nums[id])
            self.dfs(nums, id + 1, cur)
            cur.pop()
