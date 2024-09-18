"""
在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：

值 0 代表空单元格；
值 1 代表新鲜橘子；
值 2 代表腐烂的橘子。
每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。

返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
"""
class Solution(object):
    def orangesRotting(self, grid):
        """
        :type grid: List[List[int]]
        :rtype: int
        """
        dirs = [[-1,0],[1,0], [0,-1], [0,1]]
        queue = []
        neighbour = {}
        m = len(grid)
        n = len(grid[0])
        freshCount = 0
        for i in range(0, m):
            for j in range(0, n):
                neighbour[(i,j)] = []
                for dir in dirs:
                    x = i + dir[0]
                    y = j + dir[1]
                    if x >= 0 and x < m and y >= 0 and y < n:
                        neighbour[(i,j)].append((x,y))
                if grid[i][j] == 2:
                    queue.append((i,j))
                if grid[i][j] == 1:
                    freshCount += 1

        see = set()
        days = 0
        while len(queue) > 0 and freshCount > 0:
            days += 1
            print("days {}".format(days))
            t = len(queue)
            for i in range(0,t):
                i, j = queue.pop(0)
                print("stale source is {}".format((i,j)))
                for (x, y) in neighbour[(i, j)]:
                    if (x, y) not in see and grid[x][y] == 1:
                        see.add((x, y))
                        freshCount -= 1
                        print("{} is stale".format((x,y)))
                        queue.append((x, y))

        if freshCount > 0:
            return -1


        return days

