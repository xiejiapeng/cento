package sulwish;

/*
你有两个 有序 且数组内元素互不相同的数组 nums1 和 nums2 。

一条 合法路径 定义如下：

选择数组 nums1 或者 nums2 开始遍历（从下标 0 处开始）。
从左到右遍历当前数组。
如果你遇到了 nums1 和 nums2 中都存在的值，那么你可以切换路径到另一个数组对应数字处继续遍历（但在合法路径中重复数字只会被统计一次）。
得分 定义为合法路径中不同数字的和。

请你返回 所有可能 合法路径 中的最大得分。由于答案可能很大，请你将它对 10^9 + 7 取余后返回。

3 4 5
1 3 7
 */

public class L1537 {
    public int maxSum(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int[] a = new int[m];
        int[] b = new int[n];
        for (int i = m - 1; i > -1; i--){
            if(i == m - 1)a[i] = nums1[i];
            else a[i] = nums1[i] + a[i+1];
        }
        for (int i = n - 1; i > -1; i--){
            if(i == n - 1)b[i] = nums1[i];
            else b[i] = nums1[i] + b[i+1];
        }

        //
        int[][] up = new int[m + 1][n + 1];
        int[][] down = new int[m + 1][n + 1];
        for (int i = m; i > -1; i--) {
            for (int j = n; j > -1; j--) {
                if(i == m && j == n) {
                    up[i][j] = 0;
                    down[i][j] = 0;
                } else if(i == m) {
                    up[i][j] = 0;
                    down[i][j] = b[j];
                } else if(j == n) {
                    up[i][j] = a[i];
                    down[i][j] = 0;
                } else {
                    if(nums1[i] != nums2[j]) {
                        up[i][j] = nums1[i] + up[i+1][j];
                        down[i][j] = nums2[j] + down[i][j+1];
                    } else {
                        up[i][j] = Math.max(nums1[i] + up[i+1][j], nums1[i] + down[i+1][j+1]);
                        down[i][j] = Math.max(nums2[j] + down[i][j+1], nums2[j] + up[i+1][j+1]);
                    }
                }
            }
        }
        return Math.max(up[0][0], down[0][0]);
    }
}
