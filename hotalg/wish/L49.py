"""
给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。

字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
"""
class Solution(object):
    def groupAnagrams(self, strs):
        """
        :type strs: List[str]
        :rtype: List[List[str]]
        """
        all = {}
        for str in strs:
            sig = self.signature(str)
            print("str is {}, sig is {}".format(str, sig))
            if sig in all:
                all[sig].append(str)
            else:
                all[sig] = []
                all[sig].append(str)

        return list(all.values())


    def signature(self, str):
        all = dict(zip([chr(ord('a') + i) for i in range(0,26)], [0 for _ in range(0,26)]))
        for s in str:
            all[s] = all.get(s, 0) + 1

        return tuple([value for _, value in sorted(all.items())])