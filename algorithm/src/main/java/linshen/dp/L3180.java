package linshen.dp;

import java.util.Arrays;

/*
给你一个整数数组 rewardValues，长度为 n，代表奖励的值。

最初，你的总奖励 x 为 0，所有下标都是 未标记 的。你可以执行以下操作 任意次 ：

从区间 [0, n - 1] 中选择一个 未标记 的下标 i。
如果 rewardValues[i] 大于 你当前的总奖励 x，则将 rewardValues[i] 加到 x 上（即 x = x + rewardValues[i]），并 标记 下标 i。
以整数形式返回执行最优操作能够获得的 最大 总奖励。

提示：

1 <= rewardValues.length <= 2000
1 <= rewardValues[i] <= 2000
 */
public class L3180 {
    public int maxTotalReward(int[] rewardValues) {
        Arrays.sort(rewardValues);
        int n = rewardValues.length;
        int[][] dp = new int[n+1][5005];
        /*
        i,...,n-1
        j
         */
        for (int i = n - 1; i > -1; i--){
            for (int j = 0; j <= 5000; j++){
                //choose first
                if(rewardValues[i] > j) {
                    dp[i][j] = Math.max(dp[i][j], rewardValues[i] + dp[i+1][j+rewardValues[i]]);
                }
                //don't choose first
                dp[i][j] = Math.max(dp[i][j], dp[i+1][j]);
            }
        }
        return dp[0][0];
    }
}
