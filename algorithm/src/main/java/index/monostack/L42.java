package index.monostack;

/*
给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，
计算按此排列的柱子，下雨之后能接多少雨水。
 */

public class L42 {
    public int trap(int[] height) {
        int n = height.length;
        int[] left = new int[n];
        left[0] = -1;
        for (int i = 1; i < n; i++){
            left[i] = Math.max(height[i-1], left[i-1]);
        }
        int[] right = new int[n];
        right[n-1] = -1;
        for (int i = n - 2; i > -1; i--){
            right[i] = Math.max(right[i+1], height[i+1]);
        }
        int ans = 0;
        for (int i = 0; i < n; i++){
            if(left[i] == -1 || right[i] == -1)continue;
            else{
                int min = Math.min(left[i], right[i]);
                if(min > height[i]) ans += (min - height[i]);
            }
        }
        return ans;
    }
}
