"""
给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。  x
"""
class Solution(object):
    def trap(self, height):
        """
        :type height: List[int]
        :rtype: int
        """
        l = len(height)
        left = []
        right = []
        left_max = -1
        right_max = -1
        for x in height:
            left.append(left_max)
            left_max = max(left_max, x)
        for x in reversed(height):
            right.insert(0,right_max)
            right_max = max(right_max, x)

        ans = 0
        for i in range(0, l):
            h = min(left[i], right[i])
            print("{} left is {}, right is {}".format(height[i],left[i], right[i]))
            if h > height[i]:
                ans += (h - height[i])
                print("{} has capacity {}".format(height[i], (h - height[i])))

        return ans