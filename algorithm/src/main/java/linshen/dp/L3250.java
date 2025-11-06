package linshen.dp;

/*
给你一个长度为 n 的 正 整数数组 nums 。

如果两个 非负 整数数组 (arr1, arr2) 满足以下条件，我们称它们是 单调 数组对：

两个数组的长度都是 n 。
arr1 是单调 非递减 的，换句话说 arr1[0] <= arr1[1] <= ... <= arr1[n - 1] 。
arr2 是单调 非递增 的，换句话说 arr2[0] >= arr2[1] >= ... >= arr2[n - 1] 。
对于所有的 0 <= i <= n - 1 都有 arr1[i] + arr2[i] == nums[i] 。
请你返回所有 单调 数组对的数目。

由于答案可能很大，请你将它对 109 + 7 取余 后返回。

提示：

1 <= n == nums.length <= 2000
1 <= nums[i] <= 50
 */

public class L3250 {
    int mod = (int)(1e9+7);
    public int countOfPairs(int[] nums) {
        int n = nums.length;
        long[][] dp = new long[n][4];
        for (int i = n - 1; i > -1; i--){
            for (int j = 0; j <= nums[i]; j++){
                if(i == 1) {
                    System.out.println("f");
                }
                /*
                [3,2]
                -> (0,3)-(0,2) , (0,3)-(1,1), (0,3)-(2,0)
                 */
                if(i != n - 1) {
                    for (int k = 0; k <= nums[i+1]; k++){
                        int jj = nums[i] - j;
                        int kk = nums[i+1] - k;
                        if(k >= j && kk <= jj){
                            dp[i][j] += dp[i+1][k];
                            dp[i][j] %= mod;
                        }
                    }
                } else {
                    dp[i][j] = 1;
                }
            }
        }
        long cnt = 0;
        for (int j = 0; j <= nums[0]; j++){
            cnt += dp[0][j];
            cnt %= mod;
        }
        return (int)(cnt % mod);
    }

    public static void main(String[] args) {
        System.out.println(new L3250().countOfPairs(new int[]{2,3,2}));
    }
}
