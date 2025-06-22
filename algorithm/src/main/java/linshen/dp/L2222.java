package linshen.dp;

/*
给你一个下标从 0 开始的二进制字符串 s ，它表示一条街沿途的建筑类型，其中：

s[i] = '0' 表示第 i 栋建筑是一栋办公楼，
s[i] = '1' 表示第 i 栋建筑是一间餐厅。
作为市政厅的官员，你需要随机 选择 3 栋建筑。然而，为了确保多样性，选出来的 3 栋建筑 相邻 的两栋不能是同一类型。

比方说，给你 s = "001101" ，我们不能选择第 1 ，3 和 5 栋建筑，因为得到的子序列是 "011" ，
有相邻两栋建筑是同一类型，所以 不合 题意。
请你返回可以选择 3 栋建筑的 有效方案数 。
提示：

3 <= s.length <= 105
s[i] 要么是 '0' ，要么是 '1' 。
 */

public class L2222 {
    public long numberOfWays(String s) {
        int n = s.length();
        long[][][] dp = new long[n][4][2];
        if(s.charAt(n-1) == '0')dp[n-1][1][0] = 1;
        else dp[n-1][1][1] = 1;

        for (int i = n - 2; i > -1; i--) {
            for (int k = 1; k <= 3; k++){
                dp[i][k][0] = dp[i+1][k][0];
                dp[i][k][1] = dp[i+1][k][1];
            }
            if(s.charAt(i) == '0') {
                for (int k = 1; k <= 3; k++){
                    if(k == 1)dp[i][k][0] += 1;
                    else dp[i][k][0] += dp[i+1][k-1][1];
                }
            } else {
                for (int k = 1; k <= 3; k++){
                    if(k == 1)dp[i][k][1] += 1;
                    else dp[i][k][1] += dp[i+1][k-1][0];
                }
            }
        }
        return dp[0][3][0] + dp[0][3][1];
    }
}
