"""
给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
"""
class Solution(object):
    def permute(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        self.ans = []
        self.dfs(nums, [], set())
        return self.ans

    def dfs(self, nums, cur, used):
        if len(nums) == len(cur):
            self.ans.append(list(cur))
        for x, i in zip(nums, range(0, len(nums))):
            if i not in used:
                cur.append(x)
                used.add(i)
                self.dfs(nums, cur, used)
                used.discard(i)
                cur.pop()
