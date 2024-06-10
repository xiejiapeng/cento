"""
给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。

岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。

此外，你可以假设该网格的四条边均被水包围。
"""
class Solution(object):
    def numIslands(self, grid):
        """
        :type grid: List[List[str]]
        :rtype: int
        """
        n = len(grid)
        m = len(grid[0])
        self.dirs = [[-1, 0], [1, 0], [0, -1], [0, 1]]
        visited = [[False for j in range(0,m)] for i in range(0, n)]
        ans = 0
        for i in range(0,n):
            for j in range(0,m):
                if grid[i][j] == '1':
                    ans += 1
                    self.dfs(grid, visited, i,j)
        return ans

    def dfs(self, grid, visited, i, j):
        visited[i][j] = True
        for dir in self.dirs:
            u = i + dir[0]
            v = j + dir[1]
            if u >= 0 and u < len(grid) and v >= 0 and v < len(grid[0]) and grid[u][v] == '1' and not visited[u][v]:
                visited[u][v] = True
                self.dfs(grid, visited, u, v)
                grid[u][v] = 1

if __name__ == '__main__':
    grid = [
        ["1", "1", "1", "1", "0"],
        ["1", "1", "0", "1", "0"],
        ["1", "1", "0", "0", "0"],
        ["0", "0", "0", "1", "0"]
    ]
    print(Solution().numIslands(grid))