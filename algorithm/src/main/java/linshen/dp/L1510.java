package linshen.dp;

/*
Alice 和 Bob 两个人轮流玩一个游戏，Alice 先手。

一开始，有 n 个石子堆在一起。每个人轮流操作，正在操作的玩家可以从石子堆里拿走 任意 非零 平方数 个石子。

如果石子堆里没有石子了，则无法操作的玩家输掉游戏。

给你正整数 n ，且已知两个人都采取最优策略。如果 Alice 会赢得比赛，那么返回 True ，否则返回 False 。
提示：

1 <= n <= 10^5
 */

import java.util.HashSet;
import java.util.Set;

public class L1510 {
    public boolean winnerSquareGame(int n) {
        boolean[] firstWin = new boolean[n+1];
        Set<Integer> square = new HashSet<>();
        for (int i = 1; i <= n; i++){
            if(i * i <= n) square.add(i * i);
            else break;
        }
        firstWin[1] = true;
        for (int i = 2; i <= n; i++){
            boolean win = false;
            for (int x : square) {
                if(x <= i && !firstWin[i-x]) win = true;
            }
            firstWin[i] = win;
        }
        return firstWin[n];
    }
}
