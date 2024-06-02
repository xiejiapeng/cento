"""
给定一个字符串 s ，请你找出其中不含有重复字符的 最长
子串
 的长度。
"""
class Solution(object):
    def lengthOfLongestSubstring(self, s):
        """
        :type s: str
        :rtype: int
        """
        see = {}
        ans = 1
        j = 0
        for c, i in zip(s, range(0, len(s))):
            while c in see:
                if j >= i:
                    break
                r = s[j]
                see[r] = see[r] - 1
                if see[r] == 0:
                    see.pop(r)
                j += 1
            ans = max(ans, i - j + 1)
            see[c] = see.get(c, 0) + 1

        return ans

if __name__ == '__main__':
    sul = Solution()
    print(sul.lengthOfLongestSubstring("abcabcbb"))

