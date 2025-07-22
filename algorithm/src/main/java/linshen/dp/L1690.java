package linshen.dp;

/*
石子游戏中，爱丽丝和鲍勃轮流进行自己的回合，爱丽丝先开始 。

有 n 块石子排成一排。每个玩家的回合中，可以从行中 移除 最左边的石头或最右边的石头，
并获得与该行中剩余石头值之 和 相等的得分。当没有石头可移除时，得分较高者获胜。

鲍勃发现他总是输掉游戏（可怜的鲍勃，他总是输），所以他决定尽力 减小得分的差值 。
爱丽丝的目标是最大限度地 扩大得分的差值 。

给你一个整数数组 stones ，其中 stones[i] 表示 从左边开始 的第 i 个石头的值，如果爱丽丝和鲍勃都 发挥出最佳水平 ，
请返回他们 得分的差值 。

 */

public class L1690 {
    public int stoneGameVII(int[] stones) {
        int n = stones.length;
        int[] sum = new int[n+1];
        for (int i = 1; i <= n; i++){
            sum[i] = sum[i-1] + stones[i-1];
        }
        //todo h 罕见的双dp问题，记住
        int[][] dp = new int[n][n];
        int[][] x = new int[n][n];
        for (int len = 1; len <= n; len++){
            for (int i = 0; i + len - 1 < n; i++){
                int j = i + len - 1;
                if(len == 1) {
                    dp[i][j] = 0;
                } else {
                    int total = sum[j+1] - sum[i];
                    dp[i][j] = Math.max(total - stones[i] + x[i+1][j], total - stones[j] + x[i][j-1]);
                    x[i][j] = Math.min(dp[i+1][j] - (total - stones[i]), dp[i][j-1] - (total - stones[j]));
                }
            }
        }
        return dp[0][n-1];
    }

    public static void main(String[] args) {
        System.out.println(new L1690().stoneGameVII(new int[]{5,3,1,4,2}));
    }
}
