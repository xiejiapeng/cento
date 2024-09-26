package index.monostack;

/*
给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

求在该柱状图中，能够勾勒出来的矩形的最大面积。
 */

import java.util.Stack;

public class L84 {
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

    public static void main(String[] args) {
        System.out.println();
    }
}
