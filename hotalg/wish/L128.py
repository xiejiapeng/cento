"""
给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
4 1 3 2
"""
class Solution(object):
    def longestConsecutive(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        see = set()
        start = {} # key -> (key, ...)
        end = {} # key -> (..., key)
        ans = 0
        for n in nums:
            if n in see:
                continue
            else:
                # if any starts with n+1
                if n + 1 in start and n - 1 in end:
                    # (end[n-1][0], n-1) and (n+1, start[n+1][1]) => (end[n-1][0], start[n+1][1])
                    ni = (end[n-1][0], start[n+1][1])
                    x = end[n-1][0]
                    y = start[n+1][1]

                    start.pop(n+1)
                    start.pop(x)

                    end.pop(n-1)
                    end.pop(y)

                    start[ni[0]] = ni
                    end[ni[1]] = ni

                    ans = max(ans, ni[1] - ni[0] + 1)
                elif n + 1 in start:
                    ni = (n, start[n+1][1])
                    x = start[n+1][1]

                    start.pop(n+1)
                    end.pop(x)

                    start[ni[0]] = ni
                    end[ni[1]] = ni
                    ans = max(ans, ni[1] - ni[0] + 1)
                elif n - 1 in end:
                     ni = (end[n-1][0], n)

                     start.pop(end[n-1][0])
                     end.pop(n-1)

                     start[ni[0]] = ni
                     end[ni[1]] = ni

                     ans = max(ans, ni[1] - ni[0] + 1)
                else:
                    # (n,n)
                    ni = (n,n)
                    start[n] = ni
                    end[n] = ni
                    ans = max(ans, 1)
            see.add(n)

        return ans


if __name__ == '__main__':
    s = Solution()
    print(s.longestConsecutive([100, 4, 200, 1,3,2]))


