package linshen.structure.heap;

/*
给你一个大小为 m x n 的整数矩阵 grid 和一个大小为 k 的数组 queries 。

找出一个大小为 k 的数组 answer ，且满足对于每个整数 queries[i] ，你从矩阵 左上角 单元格开始，
重复以下过程：

如果 queries[i] 严格 大于你当前所处位置单元格，如果该单元格是第一次访问，则获得 1 分，
并且你可以移动到所有 4 个方向（上、下、左、右）上任一 相邻 单元格。
否则，你不能获得任何分，并且结束这一过程。
在过程结束后，answer[i] 是你可以获得的最大分数。注意，对于每个查询，你可以访问同一个单元格 多次 。

返回结果数组 answer 。
提示：
m == grid.length
n == grid[i].length
2 <= m, n <= 1000
4 <= m * n <= 105
k == queries.length
1 <= k <= 104
 */

import java.util.*;

public class L2503 {
    int[][] dirs = new int[][]{{-1,0}, {1,0},{0,-1},{0,1}};

    public int[] maxPoints(int[][] grid, int[] queries) {
        int k = queries.length;
        int[][] qs = new int[k][2];
        for (int i = 0; i < k; i++){
            qs[i] = new int[]{i, queries[i]};
        }
        Arrays.sort(qs, Comparator.comparingInt(o -> o[1]));
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] shadow = new boolean[m][n];
        int[] ans = new int[k];
        LinkedList<int[]> trim = new LinkedList<>();
        int cnt = 0;
        if(grid[0][0] < qs[0][1]){
            trim.add(new int[]{0,0});
            ans[qs[0][0]]++;
            shadow[0][0] = true;
            cnt++;
        }
        for (int[] q : qs) {
            int qn = q[1];
            Set<int[]> h = new HashSet<>();
            if(trim.isEmpty()) {
                if(grid[0][0] < qn) {
                    trim.add(new int[]{0,0});
                    shadow[0][0] = true;
                    cnt++;
                }
            }
            while (!trim.isEmpty()) {
                int[] tm = trim.poll();
                int x = tm[0];
                int y = tm[1];
                boolean have = false;
                for (int[] dir : dirs) {
                    if(x + dir[0] > -1 && x + dir[0] < m && y + dir[1] > -1 && y + dir[1] < n) {
                        if(!shadow[x+dir[0]][y+dir[1]]) {
                            if(grid[x+dir[0]][y+dir[1]] < qn){
                                shadow[x+dir[0]][y+dir[1]] = true;
                                trim.add(new int[]{x+dir[0], y+dir[1]});
//                                System.out.println("q="+q[0]+",x="+x+",y="+y+",dx="+(x+dir[0])+",dy="+(y+dir[1]));
                                cnt++;
                            }
                            else have = true;
                        }
                    }
                }
                if(have){
                    h.add(tm);
                }

            }
            trim.addAll(h);
            ans[q[0]] = cnt;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new L2503().maxPoints(new int[][]{{10,2},{2,5}}, new int[]{5,11})));
    }
}
