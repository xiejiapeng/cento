package linshen.dp;

/*
Alice 和 Bob 继续他们的石子游戏。许多堆石子 排成一行，每堆都有正整数颗石子 piles[i]。
游戏以谁手中的石子最多来决出胜负。

Alice 和 Bob 轮流进行，Alice 先开始。最初，M = 1。

在每个玩家的回合中，该玩家可以拿走剩下的 前 X 堆的所有石子，其中 1 <= X <= 2M。然后，令 M = max(M, X)。

游戏一直持续到所有石子都被拿走。

假设 Alice 和 Bob 都发挥出最佳水平，返回 Alice 可以得到的最大数量的石头。
提示：

1 <= piles.length <= 100
1 <= piles[i] <= 104
 */

public class L1140 {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[] sum = new int[n+1];
        for (int i = 1; i <= n; i++){
            sum[i] = sum[i-1] + piles[i-1];
        }
        int[][] dp = new int[n+1][n+1];
        for (int i = n - 1; i > -1; i--){
            for (int j = 1; j <= n; j++){
                int total = sum[n] - sum[i];
                if(i == n - 1) {
                    dp[i][j] = piles[i];
                } else {
                    for (int x = 1; x <= 2 * j; x++){
                        //todo m 主要这里面几个min
                        dp[i][j] = Math.max(dp[i][j], total - dp[Math.min(i+x, n)][Math.min(n, Math.max(x,j))]);
                    }
                }
            }
        }
        return dp[0][1];
    }

    public static void main(String[] args) {
        System.out.println(new L1140().stoneGameII(new int[]{2,7,9,4,4}));
    }
}
