package sulwish;

/*
给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。

'.' 匹配任意单个字符
'*' 匹配零个或多个前面的那一个元素
所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 */

public class L10 {
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        boolean[][] dp = new boolean[n][m];

        /*
            ab
            a.
            a*
            .a
            ..
            .*
            * -> invalid


         */
        for(int i = n - 1; i > -1; i--){
            for (int j = m - 1; j > -1; j--) {
            }
        }
        return false;
    }
}
