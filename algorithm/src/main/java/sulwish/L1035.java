package sulwish;

/*
在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。

现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足：

 nums1[i] == nums2[j]
且绘制的直线不与任何其他连线（非水平线）相交。
请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。

以这种方法绘制线条，并返回可以绘制的最大连线数。
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L1035 {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        Map<Integer, List<Integer>> index1 = new HashMap<>();
        Map<Integer, List<Integer>> index2 = new HashMap<>();
        for (int i = 0; i < n; i++) {
            index1.putIfAbsent(nums1[i], new ArrayList<>());
            index1.get(nums1[i]).add(i);
        }
        for (int i = 0; i < m; i++) {
            index2.putIfAbsent(nums2[i], new ArrayList<>());
            index2.get(nums2[i]).add(i);
        }

        int[][] ans = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (nums1[i] == nums2[j]) {
                    ans[i][j] = Math.max(ans[i][j], (i - 1 >= 0 && j - 1 >= 0 ? 1 + ans[i - 1][j - 1] : 1));
                }
                List<Integer> ids = index2.getOrDefault(nums1[i], new ArrayList<>());
                for (int id : ids) {
                    if (id < j) {
                        ans[i][j] = Math.max(ans[i][j], (i - 1 >= 0 && id - 1 >= 0 ? 1 + ans[i - 1][id - 1] : 1));
                    }
                }
                if (i - 1 >= 0) {
                    ans[i][j] = Math.max(ans[i][j], ans[i - 1][j]);
                }

            }
        }
        return ans[n - 1][m - 1];
    }
}
