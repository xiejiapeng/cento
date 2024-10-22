package index.seqdp;

import java.util.HashMap;
import java.util.Map;

/*
在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。

现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足：

 nums1[i] == nums2[j]
且绘制的直线不与任何其他连线（非水平线）相交。
请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。

以这种方法绘制线条，并返回可以绘制的最大连线数。

1 <= nums1.length, nums2.length <= 500
1 <= nums1[i], nums2[j] <= 2000


 */
public class L1035 {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[][] f = new int[n+1][m+1];
        int max = 0;
        for (int i = n - 1; i > - 1; i--) {
            for (int j = m - 1; j > -1; j--) {
                if(nums1[i] == nums2[j]) {
                    f[i][j] = Math.max(f[i][j], 1 + f[i+1][j+1]);
                }
                f[i][j] = Math.max(f[i][j], f[i][j+1]);
                f[i][j] = Math.max(f[i][j], f[i+1][j]);

                max = Math.max(max, f[i][j]);
            }
        }
        return max;
    }
}
