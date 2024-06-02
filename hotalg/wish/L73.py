"""
给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
"""
class Solution(object):
    def setZeroes(self, matrix):
        """
        :type matrix: List[List[int]]
        :rtype: None Do not return anything, modify matrix in-place instead.
        """
        n = len(matrix)
        m = len(matrix[0])

        row = -1
        col = -1

        for i in range(0, n):
            for j in range(0, m):
                if matrix[i][j] == 0:
                    if row == -1:
                        row = i
                        col = j
                        for t in range(0,m):
                            if matrix[i][t] != 0:
                                matrix[i][t] = 100
                        for t in range(0, n):
                            if matrix[t][j] != 0:
                                matrix[t][j] = 100

                    matrix[i][col] = 1
                    matrix[row][j] = 1

        if row != -1:
            for i in range(0, n):
                if i == row:
                    continue
                if matrix[i][col] == 1:
                    for j in range(0, m):
                        matrix[i][j] = 0

            for j in range(0, m):
                if j == col:
                    continue
                if matrix[row][j] == 1:
                    for i in range(0, n):
                        matrix[i][j] = 0

            for i in range(0, n):
                matrix[i][col] = 0

            for j in range(0, m):
                matrix[row][j] = 0



if __name__ == '__main__':
    matrix = [[1]]
    Solution().setZeroes(matrix)
    print(matrix)