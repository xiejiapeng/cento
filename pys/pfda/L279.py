"""
给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。

完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。
例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
"""
class Solution(object):
    def numSquares(self, n):
        """
        :type n: int
        :rtype: int
        """
        dp = [10000] * (n+1)
        dp[0] = 0
        squares = list()
        for i in range(1, n+1):
            if i * i <= n:
                squares.append(i * i)
            for x in squares:
                if x <= i:
                    dp[i] = min(dp[i], 1 + dp[i-x])
                else:
                    break



        return dp[n]

if __name__ == '__main__':
    print(Solution().numSquares(18))
