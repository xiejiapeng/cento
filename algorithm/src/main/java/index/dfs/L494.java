package index.dfs;

/*
给你一个非负整数数组 nums 和一个整数 target 。

向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：

例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 */

import java.util.Arrays;

public class L494 {
    //[-sum,sum]
    public int findTargetSumWays(int[] nums, int target) {
        int sum = Arrays.stream(nums).sum();
        if(target > sum || target < -1 * sum)return 0;
        int n = nums.length;
        //j -> j-sum
        int[][] dp = new int[n][2 * sum + 1];
        for (int i = n - 1; i > -1; i--) {
            for (int j = 0; j <= 2 * sum; j++) {
                int t = j - sum;
                if(i == n - 1) {
                    if(t == nums[i]){
                        dp[i][j] += 1;
                    }
                    if(t == -1 * nums[i]) {
                        dp[i][j] += 1;
                    }
                } else {
                    //+
                    if(t-nums[i]+sum>=0&&t-nums[i]+sum<=2*sum)dp[i][j] += dp[i+1][t-nums[i]+sum];
                    //-
                    if(t+nums[i]+sum>=0&&t+nums[i]+sum<=2*sum)dp[i][j] += dp[i+1][t+nums[i]+sum];
                }
            }
        }

        return dp[0][target + sum];
    }

    public static void main(String[] args) {
        System.out.println(new L494().findTargetSumWays(new int[]{1,0}, 1));
    }
}
