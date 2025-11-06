package linshen.backtrace;
/*
在二维网格 grid 上，有 4 种类型的方格：

1 表示起始方格。且只有一个起始方格。
2 表示结束方格，且只有一个结束方格。
0 表示我们可以走过的空方格。
-1 表示我们无法跨越的障碍。
返回在四个方向（上、下、左、右）上行走时，从起始方格到结束方格的不同路径的数目。

每一个无障碍方格都要通过一次，但是一条路径中不能重复通过同一个方格。
示：

1 <= grid.length * grid[0].length <= 20
 */
public class L980 {
    int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    int ans = 0;
    public int uniquePathsIII(int[][] grid) {
        int[] start = new int[2];
        int[] end = new int[2];
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1) {
                    start = new int[]{i,j};
                } else if(grid[i][j] == 2) {
                    end = new int[]{i,j};
                }
            }
        }
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        //todo h 起点设置为true
        visited[start[0]][start[1]] = true;
        dfs(grid, start, end, visited, start[0], start[1]);
        return ans;
    }

    private void dfs(int[][] grid, int[] start, int[] end, boolean[][] visited, int i, int j) {
        if(i == end[0] && j == end[1]) {
            boolean add = true;
            for(int s = 0; s < visited.length; s++) {
                for (int t = 0; t < visited[0].length; t++){
                    if(grid[s][t] == 0 && !visited[s][t]) {
                        add = false;
                        break;
                    }
                }
            }
            if(add) {
                ans++;
            }
        } else {
            for(int[] dir : dirs) {
                int x = i + dir[0];
                int y = j + dir[1];
                if(x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
                    if(!visited[x][y] && grid[x][y] != -1) {
                        visited[x][y] = true;
                        dfs(grid, start, end, visited, x, y);
                        visited[x][y] = false;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new L980().uniquePathsIII(new int[][]{{0,1},{2,0}}));
    }
}
