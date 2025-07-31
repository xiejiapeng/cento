package linshen.dp;

/*
给你一个字符串 s ，返回 s 中不同的非空回文子序列个数 。由于答案可能很大，请返回对 109 + 7 取余 的结果。

字符串的子序列可以经由字符串删除 0 个或多个字符获得。

如果一个序列与它反转后的序列一致，那么它是回文序列。

如果存在某个 i , 满足 ai != bi ，则两个序列 a1, a2, ... 和 b1, b2, ... 不同。

[i,i+1,...,j-1,j]
[a...

提示：

1 <= s.length <= 1000
s[i] 仅包含 'a', 'b', 'c' 或 'd'



todo hhhhh
 */

public class L730 {
    int mod = (int) (1e9 + 7);

    public static void main(String[] args) {
        /*
            a ->aaa
            aba ->aabaa
            aa->aaaa

            b->aba

            1[a...a] -> [a,a...a,a]1
            3[b...b] -> [a,b...b,a]3
                    -> [a]1
                    -> [a,a]1


         */
        System.out.println(new L730().countPalindromicSubsequences("ababa"));
    }

    public int countPalindromicSubsequences(String s) {
        int n = s.length();
        long[][][] dp = new long[n][n][4];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == 1) dp[i][j][s.charAt(i) - 'a'] = 1;
                else if (len == 2) {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j][s.charAt(i) - 'a'] = 2;
                    } else {
                        dp[i][j][s.charAt(i) - 'a'] = 1;
                        dp[i][j][s.charAt(j) - 'a'] = 1;
                    }
                } else {
                    //todo h 思考一下，什么时候，可以在动态规划中使用枚举；这里如果不用枚举，s[i]==s[j]可以转化为子问题，
                    // 其他情况不好转化；因为有k的限制，能把头或者尾去掉
                    // 还要就是这里要求的是所有的个数，需要通过枚举来防止重复
                    for (int k = 0; k < 4; k++) {
                        if (s.charAt(i) - 'a' == k && s.charAt(j) - 'a' == k) {
                            /*
                                这里，最初想法是将[i+1,j-1]的以k开头的都列出来，在两端加上k，构成新的序列；老的以k开始的序列，为什么没有加进来呢？
                                老的可以不加两边的k，构成最终答案啊！
                                问题就出在去重这里，老的以k开头的序列，与那些老的不以k开头的序列+两边新增k构成的序列是一样的！！！
                             */
                            dp[i][j][k] += dp[i + 1][j - 1][0];
                            dp[i][j][k] %= mod;
                            dp[i][j][k] += dp[i + 1][j - 1][1];
                            dp[i][j][k] %= mod;
                            dp[i][j][k] += dp[i + 1][j - 1][2];
                            dp[i][j][k] %= mod;
                            dp[i][j][k] += dp[i + 1][j - 1][3];
                            dp[i][j][k] %= mod;

                            //todo h 记得子问题可以为空
                            dp[i][j][k] += 2;
                            dp[i][j][k] %= mod;
                        } else if (s.charAt(i) - 'a' == k && s.charAt(j) - 'a' != k) {
                            dp[i][j][k] = dp[i][j - 1][k]; //todo h: 这种情况，依然可以以i开头，只要总区间长度小于len即可！！不要带入线性规划的思维，觉得只能依赖i+1子问题
                        } else if (s.charAt(i) - 'a' != k && s.charAt(j) - 'a' == k) {
                            dp[i][j][k] = dp[i + 1][j][k];
                        } else {
                            dp[i][j][k] = dp[i + 1][j - 1][k];
                        }
                    }
                }
            }
        }
        System.out.println(dp[1][3][0]);
        System.out.println(dp[1][3][1]);
        System.out.println(dp[1][3][2]);
        System.out.println(dp[1][3][3]);

        System.out.println(dp[0][4][0]);

        return (int) (((dp[0][n - 1][0] % mod) + (dp[0][n - 1][1] % mod) + (dp[0][n - 1][2] % mod) + (dp[0][n - 1][3] % mod)) % mod);
    }
}
