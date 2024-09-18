"""
给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
"""
class Solution(object):
    def findKthLargest(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: int
        """
        return self.find(nums, 0, len(nums)-1, len(nums)+1-k) # 返回待比较数据最终位置

    def find(self, nums, x, y, k):
        print("x={},y={},k={}".format(x,y,k))
        if x == y:
            return nums[x]
        else:
            pivot = nums[x]
            left = x
            right = x + 1
            while right <= y:
                if nums[right] <= pivot:
                    nums[left+1], nums[right] = nums[right], nums[left+1]
                    left += 1
                    right += 1
                else:
                    right += 1
            # [x,left],[left+1,y]
            if left-x+1 == k:
                return pivot
            elif left-x+1 > k:
                return self.find(nums, x+1, left, k)
            else:
                return self.find(nums, left+1, y, k-(left-x+1))



if __name__ == '__main__':
    print(Solution().partition([5,6,5,7], 0, 3))