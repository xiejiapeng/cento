"""
给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。

异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
"""
class Solution(object):
    def findAnagrams(self, s, p):
        """
        :type s: str
        :type p: str
        :rtype: List[int]
        """
        l = len(s)
        lp = len(p)
        all = {}
        sigp = self.sig(p)
        ans = []
        for i in range(0, l):
            j = i - lp
            if j >= 0:
                all[s[j]] = all.get(s[j]) - 1
                if all[s[j]] == 0:
                    all.pop(s[j])
            all[s[i]] = all.get(s[i], 0) + 1
            if all == sigp:
                ans.append(j + 1)

        return ans

    def sig(self, s):
        see = {}
        for c in s:
            see[c] = see.get(c, 0) + 1
        return see