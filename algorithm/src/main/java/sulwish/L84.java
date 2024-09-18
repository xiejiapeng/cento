package sulwish;

/*
给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

求在该柱状图中，能够勾勒出来的矩形的最大面积。
 */

import java.util.Arrays;
import java.util.Stack;

public class L84 {
    public static void main(String[] args) {
        System.out.println(new L84().largestRectangleArea(new int[]{1, 1}));
    }

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        //i左侧最大的满足h小于heights[i]的序号
        int[] left = new int[n];
        int[] right = new int[n];

        Arrays.fill(left, -1);
        Arrays.fill(right, -1);


        Stack<Integer> ls = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            if (ls.isEmpty()) {
                ls.add(i);
            } else {
                while (!ls.isEmpty() && heights[ls.peek()] >= heights[i]) {
                    ls.pop();
                }
                if (ls.isEmpty()) {
                    left[i] = -1;
                } else {
                    left[i] = ls.peek();
                }
                ls.add(i);
            }
        }

        Stack<Integer> rs = new Stack<>();
        for (int i = n - 1; i > -1; i--) {
            if (rs.isEmpty()) {
                rs.add(i);
            } else {
                while (!rs.isEmpty() && heights[rs.peek()] >= heights[i]) {
                    rs.pop();
                }
                if (rs.isEmpty()) {
                    right[i] = -1;
                } else {
                    right[i] = rs.peek();
                }
                rs.add(i);
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            int l = left[i];
            int r = right[i];
            if (l == -1 && r == -1) {
                max = Math.max(max, heights[i] * n);
            } else if (l == -1) {
                max = Math.max(max, heights[i] * r);
            } else if (r == -1) {
                max = Math.max(max, heights[i] * (n - l - 1));
            } else {
                max = Math.max(max, heights[i] * (r - l - 1));
            }
        }
        return max;
    }
}
