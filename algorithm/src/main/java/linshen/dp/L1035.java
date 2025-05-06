package linshen.dp;

/*
在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。

现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足：

nums1[i] == nums2[j]
且绘制的直线不与任何其他连线（非水平线）相交。
请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。

以这种方法绘制线条，并返回可以绘制的最大连线数。
 */

public class L1035 {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[][] dp = new int[n+1][m+1];
        for (int i = n - 1; i > -1; i--){
            for (int j = m - 1; j > -1; j--){
                if(nums1[i] == nums2[j]) {
                    dp[i][j] = 1 + dp[i+1][j+1];
                }
                dp[i][j] = Math.max(dp[i][j], dp[i+1][j]);
                dp[i][j] = Math.max(dp[i][j], dp[i][j+1]);
            }
        }
        return dp[0][0];
    }
}
