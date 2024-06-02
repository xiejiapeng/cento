"""
给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。


"""
class Solution(object):
    def spiralOrder(self, matrix):
        """
        :type matrix: List[List[int]]
        :rtype: List[int]
        """
        n = len(matrix)
        m = len(matrix[0])
        dirs = [[0,1],[1,0],[0,-1],[-1,0]]
        initial = 0
        x = 0
        y = 0
        visited = [[False for _ in range(3)] for _ in range(3)]
        cnt = 0

        cont = True
        ans = []
        while cont:
            ans.append(matrix[x][y])
            origin = initial
            loop = False
            while (x + dirs[initial][0]) >= n or (x + dirs[initial][0]) < 0 or (y + dirs[initial][1]) >= m or y + dirs[initial][1] < 0 or visited[x + dirs[initial][0]][y + dirs[initial][1]]:
                loop = True
                initial = (initial + 1) % 4
                if initial == origin:
                    break

            if loop and initial == origin:
                break
            else:
                # todo
                visited[x][y] = True
                x = x + dirs[initial][0]
                y = y + dirs[initial][1]



        return ans

if __name__ == '__main__':
    print(Solution().spiralOrder([[1,2,3],[4,5,6],[7,8,9]]))