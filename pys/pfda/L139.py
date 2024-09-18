"""
给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。

注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。


"""
class Solution(object):
    def wordBreak(self, s, wordDict):
        """
        :type s: str
        :type wordDict: List[str]
        :rtype: bool
        """
        inf = 1000000

        l = len(str)
        dp = [inf] * l
        for i in range(0, l):
            for w in wordDict:
                if s[:i].endswith(w):
                    if i == len(w):
                        dp[i] = min(dp[i], 1)
                    elif dp[i-len(w)] != inf:
                        dp[i] = min(dp[i], 1 + dp[i-len(w)])
        return dp[l-1]

if __name__ == '__main__':
    print(Solution().wordBreak("",list("")))