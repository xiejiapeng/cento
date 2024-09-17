package sulwish;

/*
给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 */

import java.util.Arrays;

public class L42 {
    public int trap(int[] height) {
        int n = height.length;
        int[] left = new int[n];
        int[] right = new int[n];

        Arrays.fill(left, Integer.MIN_VALUE);
        Arrays.fill(right, Integer.MIN_VALUE);

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < height.length; i++) {
            left[i] = max;
            max = Math.max(max, height[i]);
        }

        max = Integer.MIN_VALUE;
        for (int i = height.length - 1; i > -1; i--) {
            right[i] = max;
            max = Math.max(max, height[i]);
        }

        int total = 0;
        for (int i = 0; i < height.length; i++) {
            if (left[i] == Integer.MIN_VALUE || right[i] == Integer.MIN_VALUE) continue;
            total += Math.max(0, (Math.min(left[i], right[i]) - height[i]));
        }

        return total;

    }
}
