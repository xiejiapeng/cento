package index.monostack;

/*
给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。

rows == matrix.length
cols == matrix[0].length
1 <= row, cols <= 200
matrix[i][j] 为 '0' 或 '1'
 */

import java.util.Arrays;
import java.util.Stack;

public class L85 {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] height = new int[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if(i == 0) {
                    height[i][j] = matrix[i][j] == '1' ? 1 : 0;
                } else {
                    height[i][j] = matrix[i][j] == '1' ? (height[i-1][j] + 1) : 0;
                }
            }
        }

        int max = 0;
        for (int i = 0; i < m; i++){
            System.out.println(Arrays.toString(height[i]));
            max = Math.max(max, largestRectangleArea(height[i]));
        }
        return max;

    }

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++){
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            if(stack.isEmpty()) {
                left[i] = -1;
            } else {
                left[i] = stack.peek();
            }
            stack.add(i);
        }
        stack.clear();
        int[] right = new int[n];
        for (int i = n-1; i > -1; i--){
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            if(stack.isEmpty()) {
                right[i] = n;
            } else {
                right[i] = stack.peek();
            }
            stack.add(i);
        }

        int max = 0;
        for (int i = 0; i < n; i++){
            max = Math.max(max, heights[i] * (right[i] - left[i] - 1));
        }
        return max;
    }
}
