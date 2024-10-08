package index.dualpointer;

/*
给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0)
和 (i, height[i]) 。

找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

返回容器可以储存的最大水量。

说明：你不能倾斜容器。
 */

public class L11 {
    public int maxArea(int[] height) {
        int max = 0;
        if(height == null)return max;
        int n = height.length;
        int left = 0;
        int right = n - 1;
        while (left < right) {
            max = Math.max(max, Math.min(height[left], height[right]) * (right - left));
            if(height[left] <= height[right])left++;
            else right--;
        }
        return max;
    }
}
