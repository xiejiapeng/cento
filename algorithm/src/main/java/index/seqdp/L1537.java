package index.seqdp;

/*
你有两个 有序 且数组内元素互不相同的数组 nums1 和 nums2 。

一条 合法路径 定义如下：

选择数组 nums1 或者 nums2 开始遍历（从下标 0 处开始）。
从左到右遍历当前数组。
如果你遇到了 nums1 和 nums2 中都存在的值，那么你可以切换路径到另一个数组对应数字处继续遍历（但在合法路径中重复数字只会被统计一次）。
得分 定义为合法路径中不同数字的和。

请你返回 所有可能 合法路径 中的最大得分。由于答案可能很大，请你将它对 10^9 + 7 取余后返回。
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class L1537 {
    int mod = (int)(1e9+7);
    public int maxSum(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        Map<Integer,Integer> all1 = new HashMap<>();
        for (int i = 0; i < n; i++){
            all1.put(nums1[i], i);
        }
        Map<Integer,Integer> all2 = new HashMap<>();
        for (int i = 0; i < m; i++){
            all2.put(nums2[i], i);
        }

        long[] upperx = new long[n+1];
        Arrays.fill(upperx, -1);
        upperx[n] = 0;
        long[] downx = new long[m+1];
        Arrays.fill(downx, -1);
        downx[m] = 0;

        long x = upper(nums1, nums2, upperx, downx, all1, all2, 0);
        long y = down(nums1, nums2, upperx, downx, all1, all2, 0);

        return (int)(Math.max(x, y) % mod);
    }

    private long upper(int[] nums1, int[] nums2, long[] upper, long[] down, Map<Integer,Integer> all1, Map<Integer,Integer> all2, int i) {
        if(upper[i] != -1) {
            return upper[i];
        } else {
            long v1 = nums1[i] + upper(nums1, nums2, upper, down, all1, all2, i+1);
            upper[i] = Math.max(upper[i], v1);
            if(all2.containsKey(nums1[i])) {
                int t = all2.get(nums1[i]);
                long v2 = nums1[i] + down(nums1, nums2, upper, down, all1, all2, t + 1);
                upper[i] = Math.max(upper[i], v2);
            }
        }
        return upper[i];
    }

    private long down(int[] nums1, int[] nums2, long[] upper, long[] down, Map<Integer,Integer> all1, Map<Integer,Integer> all2, int i) {
        if(down[i] != -1) {
            return down[i];
        } else {
            long v1 = nums2[i] + down(nums1, nums2, upper, down, all1, all2, i+1);
            down[i] = Math.max(down[i], v1);
            if(all1.containsKey(nums2[i])) {
                int t = all1.get(nums2[i]);
                long v2 = nums2[i] + upper(nums1, nums2, upper, down, all1, all2, t + 1);
                down[i] = Math.max(down[i], v2);
            }
        }
        return down[i];
    }

    public static void main(String[] args) {
        System.out.println(new L1537().maxSum(new int[]{7,20}, new int[]{5,7}));
    }
}
