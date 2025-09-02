package linshen.greedy;

/*
给你一个二进制矩阵 matrix ，它的大小为 m x n ，你可以将 matrix 中的 列 按任意顺序重新排列。
请你返回最优方案下将 matrix 重新排列后，全是 1 的子矩阵面积。
 */

import java.util.Arrays;

public class L1727 {
    //todo hhh 这题没有提示没能解决；虽然是贪心，不要一上来就想贪心，先分析问题，这题以前做过类似的，求最大矩阵面积；那个方法还是适用的 —— 分行统计不同元素高度
    public int largestSubmatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] height = new int[n][m];
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++){
                if(matrix[i][j] == 0)height[i][j] = 0;
                else {
                    height[i][j] = 1 + (i-1>=0?height[i-1][j]:0);
                }
            }
        }
        int max = 0;
        for (int i = 0; i < n; i++){
            Arrays.sort(height[i]);
            for (int j = 0; j < m; j++){
                max = Math.max(max, height[i][j] * (m-j));
            }
        }
        return max;
    }
}
