package sulqn;

/*
你有两个 有序 且数组内元素互不相同的数组 nums1 和 nums2 。

一条 合法路径 定义如下：

选择数组 nums1 或者 nums2 开始遍历（从下标 0 处开始）。
从左到右遍历当前数组。
如果你遇到了 nums1 和 nums2 中都存在的值，那么你可以切换路径到另一个数组对应数字处继续遍历（但在合法路径中重复数字只会被统计一次）。
得分 定义为合法路径中不同数字的和。

请你返回 所有可能 合法路径 中的最大得分。由于答案可能很大，请你将它对 10^9 + 7 取余后返回。
 */

import java.util.ArrayList;
import java.util.List;

/*
    0,1,..c1,...c2,...c3,...
    [a,b] = sum[b+1] - sum[a]
 */
public class L1537 {
    int mod = (int) (1e9 + 7);

    public int maxSum(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        int[] s1 = new int[n + 1];
        int[] s2 = new int[m + 1];
        for (int i = 1; i <= n; i++) {
            s1[i] = nums1[i - 1] + s1[i - 1];
        }
        for (int j = 1; j <= m; j++) {
            s2[j] = nums2[j - 1] + s2[j - 1];
        }

        List<int[]> commons = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < n && j < m) {
            if (nums1[i] == nums2[j]) {
                commons.add(new int[]{i, j});
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        List<Integer> n1 = new ArrayList<>();
        List<Integer> n2 = new ArrayList<>();
        if (commons.size() == 0) {
            n1.add(s1[n]);
            n2.add(s2[m]);
        } else {
            int[] c0 = commons.get(0);
            n1.add(s1[c0[0] - 1] - s1[0]);
            n2.add(s2[c0[1] - 1] - s2[0]);
            for (int k = 0; k < commons.size(); k++) {
                int[] c = commons.get(k);
                int c1 = c[0];
                int c2 = c[1];
                if (k == commons.size() - 1) {
                    n1.add(s1[n] - s1[c1]);
                    n2.add(s2[m] - s2[c2]);
                } else {
                    n1.add(s1[commons.get(k + 1)[0] - 1] - s1[c1]);
                    n2.add(s2[commons.get(k + 1)[1] - 1] - s2[c2]);
                }
            }
        }

        int ans = 0;
        for (int l = 0; l < n1.size(); l++) {
            ans += Math.max(n1.get(l), n2.get(l));
        }
        return ans;
    }
}
