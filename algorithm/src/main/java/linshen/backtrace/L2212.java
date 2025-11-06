package linshen.backtrace;

/*
Alice 和 Bob 是一场射箭比赛中的对手。比赛规则如下：

Alice 先射 numArrows 支箭，然后 Bob 也射 numArrows 支箭。
分数按下述规则计算：
箭靶有若干整数计分区域，范围从 0 到 11 （含 0 和 11）。
箭靶上每个区域都对应一个得分 k（范围是 0 到 11），Alice 和 Bob 分别在得分 k 区域射中 ak 和 bk 支箭。
如果 ak >= bk ，那么 Alice 得 k 分。如果 ak < bk ，则 Bob 得 k 分
如果 ak == bk == 0 ，那么无人得到 k 分。
例如，Alice 和 Bob 都向计分为 11 的区域射 2 支箭，那么 Alice 得 11 分。如果 Alice 向计分为 11 的区域射 0 支箭，
但 Bob 向同一个区域射 2 支箭，那么 Bob 得 11 分。

给你整数 numArrows 和一个长度为 12 的整数数组 aliceArrows ，该数组表示 Alice 射中 0 到 11 每个计分区域的箭数量。
现在，Bob 想要尽可能 最大化 他所能获得的总分。

返回数组 bobArrows ，该数组表示 Bob 射中 0 到 11 每个 计分区域的箭数量。且 bobArrows 的总和应当等于 numArrows 。

如果存在多种方法都可以使 Bob 获得最大总分，返回其中 任意一种 即可。
提示：

1 <= numArrows <= 105
aliceArrows.length == bobArrows.length == 12
0 <= aliceArrows[i], bobArrows[i] <= numArrows
sum(aliceArrows[i]) == numArrows
 */

import java.util.LinkedList;

public class L2212 {
    //todo hhh dfs中产生分支的逻辑是 1.这一步是否放弃，选或者不选 2.这一步选择什么策略，即枚举选哪里
    int max = 0;
    LinkedList<Integer> ma = new LinkedList<>();
    public int[] maximumBobPoints(int numArrows, int[] aliceArrows) {
        dfs(numArrows, aliceArrows, 0, new LinkedList<>(), numArrows);
        return ma.stream().mapToInt(i -> i).toArray();
    }

    private void dfs(int numArrows, int[] alice, int cur, LinkedList<Integer> l, int remain) {
        if(cur == 12) {
            int score = 0;
            for (int i = 0; i < 12; i++){
                if(l.get(i) > alice[i])score += i;
            }
            if(score > max) {
                ma = new LinkedList<>(l);
                ma.set(0, ma.get(0) + remain);
                max = score;
            }
        } else {
            //larger
            if(remain > alice[cur]) {
                l.addLast(alice[cur] + 1);
                dfs(numArrows, alice, cur + 1, l, remain - alice[cur] - 1);
                l.removeLast();
            }
            l.addLast(0);
            dfs(numArrows, alice, cur + 1, l, remain);
            l.removeLast();
        }
    }
}
