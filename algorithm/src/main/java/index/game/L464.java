package index.game;

/*
在 "100 game" 这个游戏中，两名玩家轮流选择从 1 到 10 的任意整数，累计整数和，先使得累计整数和 达到或超过  100 的玩家，即为胜者。

如果我们将游戏规则改为 “玩家 不能 重复使用整数” 呢？

例如，两个玩家可以轮流从公共整数池中抽取从 1 到 15 的整数（不放回），直到累计整数和 >= 100。

给定两个整数 maxChoosableInteger （整数池中可选择的最大数）和 desiredTotal（累计和），若先出手的玩家能稳赢则返回 true ，否则返回 false 。假设两位玩家游戏时都表现 最佳 。

1 <= maxChoosableInteger <= 20
0 <= desiredTotal <= 300
 */

public class L464 {
    int n = 1 << 20;
    int[][] f = new int[n][21];

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        return false;
    }

    private boolean can(int state, int maxChoose, int cur, int desired) {
        if (f[state][desired] != -1) {
            return f[state][desired] == 1;
        } else {
            for (int i = 1; i <= maxChoose; i++) {
                if ((state & (1 << i)) != 0) {
                    if (i + cur >= desired) return true;
                    else {
                        int ns = state & (1 << i);

                    }
                }
                int ns = state & (1 << i);
            }
        }
        return false;
    }
}
