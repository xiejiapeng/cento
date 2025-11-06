package linshen.dp;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
给你一个整数 n 表示数轴上的房屋数量，编号从 0 到 n - 1 。
另给你一个二维整数数组 offers ，其中 offers[i] = [starti, endi, goldi] 表示第 i 个买家想要以 goldi 枚金币的价格购买从 starti 到 endi 的所有房屋。

作为一名销售，你需要有策略地选择并销售房屋使自己的收入最大化。

返回你可以赚取的金币的最大数目。

注意 同一所房屋不能卖给不同的买家，并且允许保留一些房屋不进行出售。
提示：

1 <= n <= 105
1 <= offers.length <= 105
offers[i].length == 3
0 <= starti <= endi <= n - 1
1 <= goldi <= 103
 */

public class L2830 {
    public int maximizeTheProfit(int n, List<List<Integer>> offers) {
        int m = offers.size();
        Collections.sort(offers, Comparator.comparingInt(o -> o.get(0)));
        int[] dp = new int[m+1];
        for (int i = m - 1; i > -1; i--) {
            if(i == m - 1)dp[i] = offers.get(i).get(2);
            else {
                //don't sell to i
                dp[i] = Math.max(dp[i], dp[i+1]);
                //sell to i
                int t = first(offers.get(i).get(1), offers, 0, offers.size() - 1);
                if(t != -1) {
                    dp[i] = Math.max(dp[i], offers.get(i).get(2) + dp[t]);
                } else {
                    dp[i] = Math.max(dp[i], offers.get(i).get(2));
                }
            }
        }
        return dp[0];
    }

    private int first(int x, List<List<Integer>> offers, int start, int end){
        if(start == end) {
            if(offers.get(start).get(0) > x)return start;
            else return -1;
        } else if(start == end - 1) {
            if(offers.get(start).get(0) > x)return start;
            else if(offers.get(end).get(0) > x)return end;
            else return -1;
        } else {
            int mid = (start + end) / 2;
            if(offers.get(mid).get(0) > x)return first(x, offers, start, mid);
            else return first(x, offers, mid + 1, end);
        }
    }

    public static void main(String[] args) {
        System.out.println(new L2830().maximizeTheProfit(5, Arrays.asList(Arrays.asList(0,0,1), Arrays.asList(0,2,10), Arrays.asList(1,3,2))));
    }
}
