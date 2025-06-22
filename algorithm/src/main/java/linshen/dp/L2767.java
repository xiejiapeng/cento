package linshen.dp;

/*
给你一个二进制字符串 s ，你需要将字符串分割成一个或者多个 子字符串 ，使每个子字符串都是 美丽 的。

如果一个字符串满足以下条件，我们称它是 美丽 的：

它不包含前导 0 。
它是 5 的幂的 二进制 表示。
请你返回分割后的子字符串的 最少 数目。如果无法将字符串 s 分割成美丽子字符串，请你返回 -1 。

子字符串是一个字符串中一段连续的字符序列。

提示：

1 <= s.length <= 15
s[i] 要么是 '0' 要么是 '1' 。
 */

import java.util.HashSet;
import java.util.Set;

public class L2767 {
    int up = (int)(Math.pow(2, 16))-1;
    public int minimumBeautifulSubstrings(String s) {
        int t = 1;
        Set<String> dict = new HashSet<>();
        while (t <= up) {
            String x = Integer.toBinaryString(t);
            for (int i = 0; i < x.length(); i++){
                if(x.charAt(i) != '0'){
                    dict.add(x.substring(i));
                    //todo m 记得及时break
                    break;
                }
            }
            t *= 5;
        }
        int n = s.length();
        int[] dp = new int[n+1];
        for (int i = n - 1; i > -1; i--) {
            if(s.charAt(i) == '0')dp[i] = -1;
            else {
                dp[i] = Integer.MAX_VALUE;
                if(dict.contains(s.substring(i)))dp[i] = n - i;
                for (int j = i; j < n; j++){
                    if(dp[j+1] == -1)continue;
                    if(!dict.contains(s.substring(i,j+1)))continue;
                    else {
                        dp[i] = Math.min(dp[i], 1 + dp[j+1]);
                        if(i == 0 && dp[i] == 2) {
                            System.out.println("F");
                        }
                    }
                }
                if(dp[i] == Integer.MAX_VALUE)dp[i] = -1;
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(new L2767().minimumBeautifulSubstrings("100111000111101"));
    }
}
