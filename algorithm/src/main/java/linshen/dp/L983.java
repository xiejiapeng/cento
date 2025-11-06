package linshen.dp;

/*
在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行。在接下来的一年里，
你要旅行的日子将以一个名为 days 的数组给出。每一项是一个从 1 到 365 的整数。

火车票有 三种不同的销售方式 ：

一张 为期一天 的通行证售价为 costs[0] 美元；
一张 为期七天 的通行证售价为 costs[1] 美元；
一张 为期三十天 的通行证售价为 costs[2] 美元。
通行证允许数天无限制的旅行。 例如，如果我们在第 2 天获得一张 为期 7 天 的通行证，那么我们可以连着旅行 7 天：第 2 天、第 3 天、第 4 天、第 5 天、第 6 天、第 7 天和第 8 天。

返回 你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费 。
提示：

1 <= days.length <= 365
1 <= days[i] <= 365
days 按顺序严格递增
costs.length == 3
1 <= costs[i] <= 1000
 */

public class L983 {
    public int mincostTickets(int[] days, int[] costs) {
        int n = days.length;
        int[] dp = new int[n+1];
        for (int i = n - 1; i > -1; i--) {
            dp[i] = costs[0] + dp[i+1];
            for (int j = i; j < n; j++){
                if(days[j] - days[i] <= 6)dp[i] = Math.min(dp[i], costs[1] + dp[j+1]);
                if(days[j] - days[i] <= 29)dp[i] = Math.min(dp[i], costs[2] + dp[j+1]);
                else break;
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(new L983().mincostTickets(new int[]{1,4,6,7,8,20}, new int[]{2,7,15}));
    }
}
