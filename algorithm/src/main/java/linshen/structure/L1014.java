package linshen.structure;

/*
给你一个正整数数组 values，其中 values[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的 距离 为 j - i。

一对景点（i < j）组成的观光组合的得分为 values[i] + values[j] + i - j ，也就是景点的评分之和 减去 它们两者之间的距离。

返回一对观光景点能取得的最高分。
提示：
2 <= values.length <= 5 * 104
1 <= values[i] <= 1000
 */

public class L1014 {
    public int maxScoreSightseeingPair(int[] values) {
        int n = values.length;
        int[] right = new int[n+1];
        right[n-1] = Integer.MIN_VALUE;
        for (int i = n - 2; i > -1; i--) {
            right[i] = Math.max(right[i+1], values[i+1] - (i+1));
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++){
            max = Math.max(max, values[i] + i + right[i]);
        }
        return max;
    }
}
