"""
编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：

每行的元素从左到右升序排列。
每列的元素从上到下升序排列。

"""
class Solution(object):
    def searchMatrix(self, matrix, target):
        """
        :type matrix: List[List[int]]
        :type target: int
        :rtype: bool
        """
        return self.search(matrix, 0, len(matrix) - 1, 0, len(matrix[0]) - 1, target)

    def search(self, matrix, sr, er, sc, ec, target):
        if sr > er or sc > ec:
            return False
        elif sr == er and sc == ec:
            return matrix[sr][sc] == target
        else:
            if matrix[sr][ec] == target:
                return True
            elif matrix[sr][ec] > target:
                return self.search(matrix, sr, er, sc, ec - 1, target)
            else:
                return self.search(matrix, sr + 1, er, sc, ec, target)