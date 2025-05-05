package linshen.dp;

/*
给定一个整数数组 arr 和一个整数 k ，通过重复 k 次来修改数组。

例如，如果 arr = [1, 2] ， k = 3 ，那么修改后的数组将是 [1, 2, 1, 2, 1, 2] 。

返回修改后的数组中的最大的子数组之和。注意，子数组长度可以是 0，在这种情况下它的总和也是 0。

由于 结果可能会很大，需要返回的 109 + 7 的 模 。
提示：

1 <= arr.length <= 105
1 <= k <= 105
-104 <= arr[i] <= 104
 */
public class L1191 {
    int mod = (int)(1e9+7);
    public int kConcatenationMaxSum(int[] nums, int k) {
        int n = nums.length;
        long[] s = new long[n+1];
        for (int i = 1; i <= n; i++){
            s[i] = s[i-1] + nums[i-1];
        }
        //don't cross the array
        long min = 0;
        long ans = 0;
        for (int i = 1; i <= n; i++){
            ans = Math.max(ans, s[i] - min);
            min = Math.min(min, s[i]);
        }
        //cross the array
        long total = s[n];
        long left = 0;
        for (int i = 0; i <= n; i++){
            left = Math.max(left, total - s[i]);
        }
        long right = 0;
        for (int i = 0; i <= n; i++){
            right = Math.max(right, s[i]);
        }
        if(k == 1) {
            return (int)(ans % mod);
        } else if(k == 2) {
            return (int)(Math.max(ans, left + right) % mod);
        } else {
            if(total > 0) {
                long x = left + right + (k-2) * total;
                long a = Math.max(ans, x);
                return (int)(a % mod);
            } else {
                long x = left + right;
                long a = Math.max(ans, x);
                return (int)(a % mod);
            }
        }


    }

    public static void main(String[] args) {
        System.out.println(new L1191().kConcatenationMaxSum(new int[]{1,2}, 3));
    }
}
