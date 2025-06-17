package linshen.dp;

/*
一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：

"1" -> 'A'
 "2" -> 'B'
 ...
 "25" -> 'Y'
 "26" -> 'Z'

然而，在 解码 已编码的消息时，你意识到有许多不同的方式来解码，因为有些编码被包含在其它编码当中（"2" 和 "5" 与 "25"）。

例如，"11106" 可以映射为：

"AAJF" ，将消息分组为 (1, 1, 10, 6)
"KJF" ，将消息分组为 (11, 10, 6)
消息不能分组为 (1, 11, 06) ，因为 "06" 不是一个合法编码（只有 "6" 是合法的）。
注意，可能存在无法解码的字符串。

给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。如果没有合法的方式解码整个字符串，返回 0。

题目数据保证答案肯定是一个 32 位 的整数。
提示：

1 <= s.length <= 100
s 只包含数字，并且可能包含前导零。
 */

public class L91 {
    public int numDecodings(String s) {
        if(s.startsWith("0"))return 0;
        int n = s.length();
        int[] dp = new int[n];
        for (int i = n - 1; i > -1; i--) {
            if(s.charAt(i) == '0')dp[i] = 0;
            else {
                for (int j = 1; j <= 26; j++){
                    String t = String.valueOf(j);
                    if(s.substring(i).startsWith(t)) {
                        if(i + t.length() < n) {
                            if(s.charAt(i + t.length()) != '0') {
                                dp[i] += dp[i+t.length()];
                            }
                        } else if(i + t.length() == n) {
                            dp[i] += 1;
                        }
                    }

                }
            }
        }
        return dp[0];
    }
}
