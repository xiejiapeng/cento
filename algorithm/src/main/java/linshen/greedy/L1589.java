package linshen.greedy;

/*
有一个整数数组 nums ，和一个查询数组 requests ，
其中 requests[i] = [starti, endi] 。
第 i 个查询求 nums[starti] + nums[starti + 1] + ... + nums[endi - 1] + nums[endi]
的结果 ，starti 和 endi 数组索引都是 从 0 开始 的。

你可以任意排列 nums 中的数字，请你返回所有查询结果之和的最大值。

由于答案可能会很大，请你将它对 109 + 7 取余 后返回。

提示：

n == nums.length
1 <= n <= 105
0 <= nums[i] <= 105
1 <= requests.length <= 105
requests[i].length == 2
0 <= starti <= endi < n

提示：

n == nums.length
1 <= n <= 105
0 <= nums[i] <= 105
1 <= requests.length <= 105
requests[i].length == 2
0 <= starti <= endi < n
 */

import java.util.Arrays;

public class L1589 {
    int mod = (int)(1e9+7);
    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        int n = nums.length;
        int[] diff = new int[n+1];
        int[] f = new int[n];
        for (int[] req : requests) {
            int start = req[0];
            int end = req[1];
            diff[start]++;
            diff[end+1]--;
        }
        int x = 0;
        for (int i = 0; i < n; i++){
            x += diff[i];
            f[i] = x;
        }
        Arrays.sort(nums);
        Arrays.sort(f);
        long ans = 0;
        for (int i = n - 1; i > -1; i--){
            ans += (long)nums[i] * f[i];
            ans %= mod;
        }
        return (int)(ans % mod);
    }
}
