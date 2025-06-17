package linshen.dp;

/*
某个程序本来应该输出一个整数数组。但是这个程序忘记输出空格了以致输出了一个数字字符串，
我们所知道的信息只有：数组中所有整数都在 [1, k] 之间，且数组中的数字都没有前导 0 。

给你字符串 s 和整数 k 。可能会有多种不同的数组恢复结果。

按照上述程序，请你返回所有可能输出字符串 s 的数组方案数。

由于数组方案数可能会很大，请你返回它对 10^9 + 7 取余 后的结果。
提示：

1 <= s.length <= 10^5.
s 只包含数字且不包含前导 0 。
1 <= k <= 10^9.
 */

public class L1416 {
    int mod = (int)(1e9+7);
    public int numberOfArrays(String s, int k) {
        int n = s.length();
        long[] dp = new long[n+1];
        dp[n] = 1;
        for (int i = n - 1; i > -1; i--) {
            if(s.charAt(i) == '0')dp[i] = 0;
            else {
                for (int j = i; j < n; j++) {
                    long t = Long.parseLong(s.substring(i, j+1));
                    if(t <= k) {
                        dp[i] += dp[j+1];
                        dp[i] %= mod;
                    } else {
                        break;
                    }
                }
            }
        }
        return (int)(dp[0] % mod);
    }
}
