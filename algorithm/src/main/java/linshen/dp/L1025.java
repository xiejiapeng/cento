package linshen.dp;

/*
爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。

最初，黑板上有一个数字 n 。在每个玩家的回合，玩家需要执行以下操作：

选出任一 x，满足 0 < x < n 且 n % x == 0 。
用 n - x 替换黑板上的数字 n 。
如果玩家无法执行这些操作，就会输掉游戏。

只有在爱丽丝在游戏中取得胜利时才返回 true 。假设两个玩家都以最佳状态参与游戏。
提示：

1 <= n <= 1000
 */

public class L1025 {
    public boolean divisorGame(int n) {
        //dp[i] -> first will lose for i
        boolean[] loss = new boolean[n+1];
        loss[1] = true;
        for (int i = 2; i <= n; i++){
            boolean canWin = false;
            for (int x = 1; x < i; x++){
                //todo m 子问题，不要用 n % x或者 n - x，而是应该用i
                if(i % x == 0) {
                    if(loss[i-x])canWin=true;
                }
            }
            loss[i] = !canWin;
        }
        return !loss[n];
    }

    public static void main(String[] args) {
        System.out.println(new L1025().divisorGame(3));
    }
}
