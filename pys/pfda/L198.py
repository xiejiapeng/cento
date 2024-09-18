"""
你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
"""
class Solution(object):
    def rob(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        l = len(nums)
        dp = [0] * l
        for i in range(l-1, -1, -1):
            if i + 1 >= l:
                dp[i] = nums[i]
            elif i + 2 >= l:
                dp[i] = max(nums[i], dp[i + 1])
            else:
                dp[i] = max(nums[i] + dp[i+2], dp[i+1])

        return dp[0]
