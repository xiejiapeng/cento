package linshen.dp;

/*
Alice 和 Bob 继续他们的石子游戏。几堆石子 排成一行 ，每堆石子都对应一个得分，由数组 stoneValue 给出。

Alice 和 Bob 轮流取石子，Alice 总是先开始。在每个玩家的回合中，
该玩家可以拿走剩下石子中的的前 1、2 或 3 堆石子 。比赛一直持续到所有石头都被拿走。

每个玩家的最终得分为他所拿到的每堆石子的对应得分之和。每个玩家的初始分数都是 0 。

比赛的目标是决出最高分，得分最高的选手将会赢得比赛，比赛也可能会出现平局。

假设 Alice 和 Bob 都采取 最优策略 。

如果 Alice 赢了就返回 "Alice" ，Bob 赢了就返回 "Bob"，分数相同返回 "Tie" 。

todo m
 */

public class L1406 {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] sum = new int[n+1];
        for (int i = 1; i <= n; i++){
            sum[i] = sum[i-1] + stoneValue[i-1];
        }
        int[] dp = new int[n+1];
        for (int i = n - 1; i > -1; i--){
            if(i == n - 1) {
                //注意stone可能为负数，所以不能将i==n-2,i==n-3的情况写死
                dp[i] = stoneValue[i];
            } else {
                int total = sum[n] - sum[i];
                dp[i] = total - dp[i+1];
                //注意，是小于等于n，因为可以全部拿完
                if(i+2<=n)dp[i] = Math.max(dp[i], total - dp[i+2]);
                if(i+3<=n)dp[i] = Math.max(dp[i], total - dp[i+3]);
            }
        }
        int x = dp[0];
        int y = sum[n] - dp[0];
        if(x > y){
            return "Alice";
        } else if(x < y) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}
