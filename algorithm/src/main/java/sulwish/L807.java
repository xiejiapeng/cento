package sulwish;

/*
给你一座由 n x n 个街区组成的城市，每个街区都包含一座立方体建筑。给你一个下标从 0 开始的 n x n 整数矩阵 grid ，
其中 grid[r][c] 表示坐落于 r 行 c 列的建筑物的 高度 。

城市的 天际线 是从远处观察城市时，所有建筑物形成的外部轮廓。
从东、南、西、北四个主要方向观测到的 天际线 可能不同。

我们被允许为 任意数量的建筑物 的高度增加 任意增量（不同建筑物的增量可能不同） 。
高度为 0 的建筑物的高度也可以增加。然而，增加的建筑物高度 不能影响 从任何主要方向观察城市得到的 天际线 。

在 不改变 从任何主要方向观测到的城市 天际线 的前提下，返回建筑物可以增加的
最大高度增量总和 。
 */

import java.util.*;

public class L807 {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int ans = 0;
        int n = grid.length;
        int m = grid[0].length;

        Map<int[],Integer> east = new HashMap<>();
        Map<int[],Integer> west = new HashMap<>();
        Map<int[],Integer> south = new HashMap<>();
        Map<int[],Integer> north = new HashMap<>();

        for (int i = 0 ; i < n; i++){
            Stack<Integer> stack = new Stack<>();
            for (int j = 0; j < m; j++) {
                if(stack.isEmpty()){
                    stack.add(grid[i][j]);
                } else {
                    if(grid[i][j] > stack.peek()) {
                        stack.add(grid[i][j]);
                    } else if(grid[i][j] == stack.peek()) {
                        continue;
                    } else {
                        south.put(new int[]{i,j}, (stack.peek() - grid[i][j]));
                    }
                }
            }
        }

        for (int i = n-1 ; i >-1; i--){
            Stack<Integer> stack = new Stack<>();
            for (int j = m - 1; j > -1; j--) {
                if(stack.isEmpty()){
                    stack.add(grid[i][j]);
                } else {
                    if(grid[i][j] > stack.peek()) {
                        stack.add(grid[i][j]);
                    } else if(grid[i][j] == stack.peek()) {
                        continue;
                    } else {
                        north.put(new int[]{i,j}, (stack.peek() - grid[i][j]));
                    }
                }
            }
        }

        for (int j = 0 ; j < m; j++){
            Stack<Integer> stack = new Stack<>();
            for (int i = n - 1; i > -1; i--) {
                if(stack.isEmpty()){
                    stack.add(grid[i][j]);
                } else {
                    if(grid[i][j] > stack.peek()) {
                        stack.add(grid[i][j]);
                    } else if(grid[i][j] == stack.peek()) {
                        continue;
                    } else {
                        east.put(new int[]{i,j}, (stack.peek() - grid[i][j]));
                    }
                }
            }
        }

        for (int j = m - 1 ; j > -1; j--){
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < n; i++) {
                if(stack.isEmpty()){
                    stack.add(grid[i][j]);
                } else {
                    if(grid[i][j] > stack.peek()) {
                        stack.add(grid[i][j]);
                    } else if(grid[i][j] == stack.peek()) {
                        continue;
                    } else {
                        west.put(new int[]{i,j}, (stack.peek() - grid[i][j]));
                    }
                }
            }
        }
        for (int[] x : east.keySet()){
            if(west.containsKey(x) && north.containsKey(x) && south.containsKey(x)) {
                ans += Math.min(east.get(x), Math.min(west.get(x), Math.min(north.get(x), south.get(x))));
            }
        }
        System.out.println(east);
        System.out.println(west);
        System.out.println(north);
        System.out.println(south);
        return ans;
    }
}
