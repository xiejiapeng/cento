package linshen.structure;

/*
给你一个长度为 n 的数组 nums ，该数组由从 1 到 n 的 不同 整数组成。另给你一个正整数 k 。
统计并返回 nums 中的 中位数 等于 k 的非空子数组的数目。
注意：
数组的中位数是按 递增 顺序排列后位于 中间 的那个元素，如果数组长度为偶数，则中位数是位于中间靠 左 的那个元素。
subarray:
    with x <= k and x > k, or
    with x < k and x > k

提示：
n == nums.length
1 <= n <= 105
1 <= nums[i], k <= n
nums 中的整数互不相同
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class L2488 {
    public int countSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] sig = new int[n];
        //需要和为n+100或者n+101
        //todo hhhhhh 注意尝试使用构造法来将个数转化为和；另外仔细总结，为什么将k的值构造为一个很大的数，比如n+100
        for (int i = 0; i < n; i++){
            if(nums[i] < k)sig[i] = -1;
            else if(nums[i] > k)sig[i] = 1;
            else sig[i] = n + 100;
        }
        return cnt(sig, n + 100);
    }

    private int cnt(int[] sig, int target) {
        int n = sig.length;
        int[] sum = new int[n+1];
        for (int i = 1; i <= n; i++){
            sum[i] = sum[i-1] + sig[i-1];
        }
        Map<Integer, Integer> see = new HashMap<>();
        see.put(0 ,1);
        int cnt = 0;
        for (int i = 0 ; i < n; i++){
            int c1 = see.getOrDefault(sum[i+1] - target, 0);
            int c2 = see.getOrDefault(sum[i+1] - target - 1, 0);
            cnt += c1;
            cnt += c2;
            see.put(sum[i+1], see.getOrDefault(sum[i+1], 0) + 1);
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(new L2488().countSubarrays(new int[]{10,3,8,5,6,7,2,9,4,1}, 9));
    }
}
