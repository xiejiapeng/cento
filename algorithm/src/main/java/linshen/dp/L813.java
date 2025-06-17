package linshen.dp;

/*
给定数组 nums 和一个整数 k 。我们将给定的数组 nums 分成 最多 k 个非空子数组，且数组内部是连续的 。 分数 由每个子数组内的平均值的总和构成。

注意我们必须使用 nums 数组中的每一个数进行分组，并且分数不一定需要是整数。

返回我们所能得到的最大 分数 是多少。答案误差在 10-6 内被视为是正确的。

提示:
1 <= nums.length <= 100
1 <= nums[i] <= 104
1 <= k <= nums.length
 */

public class L813 {
    public double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        double[][] dp = new double[n+1][k+1];
        double[] sum = new double[n+1];
        for (int i = 0; i < n; i++) {
            sum[i+1] = sum[i] + nums[i];
        }
        for (int j = 0; j <= k; j++) {
            for (int i = n - 1; i > -1; i--) {
                if(j == 0 || j > (n - i))dp[i][j] = 0;
                else if(j == 1) {
                    dp[i][j] = (sum[n] - sum[i]) / (n - i);
                }
                else {
                    for (int t = i; t < n; t++){
                        double avg = (sum[t+1] - sum[i]) / (t-i+1);
                        dp[i][j] = Math.max(dp[i][j], avg + dp[t+1][j-1]);
                    }
                }
            }
        }
        double max = -1;
        for (int j = 0; j <= k; j++){
            max = Math.max(max, dp[0][j]);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new L813().largestSumOfAverages(new int[]{9,1,2,3,9}, 3));
    }
}
