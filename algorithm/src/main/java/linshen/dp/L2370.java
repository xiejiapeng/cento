package linshen.dp;

/*
给你一个由小写字母组成的字符串 s ，和一个整数 k 。如果满足下述条件，则可以将字符串 t 视作是 理想字符串 ：

t 是字符串 s 的一个子序列。
t 中每两个 相邻 字母在字母表中位次的绝对差值小于或等于 k 。
返回 最长 理想字符串的长度。

字符串的子序列同样是一个字符串，并且子序列还满足：可以经由其他字符串删除某些字符（也可以不删除）但不改变剩余字符的顺序得到。

注意：字母表顺序不会循环。例如，'a' 和 'z' 在字母表中位次的绝对差值是 25 ，而不是 1 。

提示：

1 <= s.length <= 105
0 <= k <= 25
s 由小写英文字母组成
 */

public class L2370 {
    //todo h 看似不可能O(n)的时候，利用全部都是小写字母的特性添加一个维度，时间复杂度不会增加太多
    public int longestIdealString(String s, int k) {
        int n = s.length();
        int[][] dp = new int[n][26];
        for (int i = n - 1; i > -1; i--) {
            if(i == n - 1) {
                dp[i][s.charAt(i)-'a'] = 1;
            } else {
                if(i == 4) {
                    System.out.println("f");
                }
                int t = s.charAt(i) - 'a';
                for (int j = 0; j < 26; j++){
                    dp[i][j] = dp[i+1][j];
                }
                for (int j = 0; j < 26; j++){
                    if(Math.abs(j-t) <= k){
                        dp[i][t] = Math.max(dp[i][t], 1 + dp[i+1][j]);
                        if(dp[i][t] == 3) {
                            System.out.println("i="+i+",t="+t);
                        }
                    }
                }
            }
        }
        int max = 1;
        for (int j = 0; j < 26; j++){
            max = Math.max(max, dp[0][j]);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new L2370().longestIdealString("pvjcci", 4));
    }
}
