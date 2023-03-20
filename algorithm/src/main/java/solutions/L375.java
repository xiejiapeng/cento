package solutions;

/*
我们正在玩一个猜数游戏，游戏规则如下：

我从1到 n 之间选择一个数字。
你来猜我选了哪个数字。
如果你猜到正确的数字，就会 赢得游戏 。
如果你猜错了，那么我会告诉你，我选的数字比你的 更大或者更小 ，并且你需要继续猜数。
每当你猜了数字 x 并且猜错了的时候，你需要支付金额为 x 的现金。如果你花光了钱，就会 输掉游戏 。
给你一个特定的数字 n ，返回能够 确保你获胜 的最小现金数，不管我选择那个数字 。

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/guess-number-higher-or-lower-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L375 {
    public int getMoneyAmount(int n) {
        int[][] f = new int[n+1][n+1];
        for(int len = 1; len <= n; len++){
            for(int i = 1; i + len - 1 <= n; i++){
                int j = i + len - 1;
                if(i == j)f[i][j] = 0;
                else {
                    f[i][j] = Integer.MAX_VALUE;
                    for(int t = i; t <= j; t++){
                        f[i][j] = Math.min(f[i][j], t + Math.max(t-1<=i?0:f[i][t-1],t+1>=j?0:f[t+1][j]));
                    }
                }
            }
        }
        return f[1][n];
    }
}
