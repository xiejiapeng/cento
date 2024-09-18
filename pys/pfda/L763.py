"""
给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。

注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。

返回一个表示每个字符串片段的长度的列表。
"""
class Solution(object):
    def partitionLabels(self, s):
        """
        :type s: str
        :rtype: List[int]
        """
        idx = [0] * 26
        for c, i in zip(s, range(0, len(s))):
            idx[ord(c) - ord('a')] = i

        start = 0
        pos = start
        limit = idx[ord(s[pos])-ord('a')]
        step = 0
        l = 0
        ans = []
        while start < len(s):
            while pos <= limit:
                limit = max(limit, idx[ord(s[pos])-ord('a')])
                l += 1
                pos += 1
            start = pos
            step += 1
            ans.append(l)
            l = 0
            if start >= len(s):
                break
            limit = idx[ord(s[pos]) - ord('a')]


        return ans

if __name__ == '__main__':
    print(Solution().partitionLabels('eaaaabaaec'))