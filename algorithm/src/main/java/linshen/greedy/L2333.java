package linshen.greedy;

/*
给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，长度为 n 。

数组 nums1 和 nums2 的 差值平方和 定义为所有满足 0 <= i < n 的 (nums1[i] - nums2[i])2 之和。

同时给你两个正整数 k1 和 k2 。你可以将 nums1 中的任意元素 +1 或者 -1 至多 k1 次。类似的，你可以将 nums2 中的任意元素 +1 或者 -1 至多 k2 次。

请你返回修改数组 nums1 至多 k1 次且修改数组 nums2 至多 k2 次后的最小 差值平方和 。

注意：你可以将数组中的元素变成 负 整数。
提示：

n == nums1.length == nums2.length
1 <= n <= 105
0 <= nums1[i], nums2[i] <= 105
0 <= k1, k2 <= 109
 */

import java.util.Arrays;

public class L2333 {
    //todo hhh 坑特别多，但是思路需要记住
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = k1 + k2;
        long[] all = new long[n];
        for (int i = 0; i < n; i++){
            all[i] = (long)Math.abs(nums1[i] - nums2[i]);
        }
        Arrays.sort(all);
        long max = all[n-1];
        int mc = 0;
        for (int i = n - 1; i > -1; i--) {
            if(all[i] == max)mc++;
            else {
                long diff = max - all[i];
                if(mc * diff <= k) {
                    k  -= mc * diff;
                    mc++;
                    max = all[i];
                } else {
                    long ans = 0;
                    for (int j = 0; j <= i; j++){
                        ans += all[j] * all[j];
                    }
                    //todo 记住，不要把mc中的若干max直接降低diff，而是轮流把max削平，这里需要削平round轮
                    long round = k / mc;
                    max -= round;
                    k -= round * mc;
                    long t = (max-1)*(max-1)*k + (mc-k)*(max*max);
                    return ans + t;
                }
            }
        }
        //todo 退出循环，不代表答案为0，如果k > 0还需要再进行一次削平；即使k=0，答案也不为0，还有算一下再返回
        if(k > 0){
            if(k >= n * max)return 0;
            long round = k / n;
            max -= round;
            k -= round * n;
            return (max-1)*(max-1)*k+(n-k)*(max*max);
        } else {
            return max*max*n;
        }

    }

    public static void main(String[] args) {
        System.out.println(new L2333().minSumSquareDiff(new int[]{18,4,8,19,13,8}, new int[]{18,11,8,2,13,15}, 16, 8));
    }
}
