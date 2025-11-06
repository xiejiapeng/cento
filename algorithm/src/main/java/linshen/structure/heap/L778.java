package linshen.structure.heap;

/*
在一个 n x n 的整数矩阵 grid 中，每一个方格的值 grid[i][j] 表示位置 (i, j) 的平台高度。

当开始下雨时，在时间为 t 时，水池中的水位为 t 。你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位必须同时淹没这两个平台。假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。当然，在你游泳的时候你必须待在坐标方格里面。

你从坐标方格的左上平台 (0，0) 出发。返回 你到达坐标方格的右下平台 (n-1, n-1) 所需的最少时间 。
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class L778 {
    //todo hhhhh 典型题，记一下吧
    int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int[][] time = new int[n][n];
        for (int i = 0; i < n; i++){
            Arrays.fill(time[i], Integer.MAX_VALUE);
        }
        PriorityQueue<String> all = new PriorityQueue<>((o1, o2) -> {
            int[] x = decode(o1);
            int[] y = decode(o2);
            return Integer.compare(time[x[0]][x[1]], time[y[0]][y[1]]);
        });
        time[0][0] = grid[0][0];
        all.add(encode(new int[]{0,0}));
        while (!all.isEmpty()) {
            int[] t = decode(all.poll());
            for (int[] dir : dirs) {
                if(t[0] + dir[0] > -1 && t[0] + dir[0] < n && t[1] + dir[1] > -1 && t[1] + dir[1] < n) {
                    int[] s = new int[]{t[0]+dir[0], t[1]+dir[1]};
                    if(Math.max(time[t[0]][t[1]], grid[s[0]][s[1]]) < time[s[0]][s[1]]) {
                        time[s[0]][s[1]] = Math.max(time[t[0]][t[1]], grid[s[0]][s[1]]);
                        all.remove(encode(s));
                        all.add(encode(s));
                    }
                }
            }
        }
        return time[n-1][n-1];
    }

    private String encode(int[] x) {
        return x[0] + "," + x[1];
    }

    private int[] decode(String s){
        String[] x = s.split(",");
        int[] a = new int[2];
        a[0] = Integer.parseInt(x[0]);
        a[1] = Integer.parseInt(x[1]);
        return a;
    }

    public static void main(String[] args) {
        System.out.println(new L778().swimInWater(new int[][]{{0,2},{1,3}}));
    }
}
