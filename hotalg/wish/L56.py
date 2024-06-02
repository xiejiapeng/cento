"""
以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
"""
class Solution(object):
    def merge(self, intervals):
        """
        :type intervals: List[List[int]]
        :rtype: List[List[int]]
        """
        if not intervals or len(intervals) == 0:
            return intervals

        ans = []
        intervals = sorted(intervals,key=lambda x:x[0])
        start = intervals[0][0]
        end = intervals[0][1]

        for interval in intervals:
            if interval[0] <= end:
                end = max(end, interval[1])
            else:
                ans.append([start, end])
                start = interval[0]
                end = interval[1]

        ans.append([start, end])
        return ans

if __name__ == '__main__':
    solu = Solution()
    print(solu.merge([[1,4],[0,4]]))


