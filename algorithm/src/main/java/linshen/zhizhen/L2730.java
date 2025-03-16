package linshen.zhizhen;

/*
给你一个下标从 0 开始的字符串 s ，这个字符串只包含 0 到 9 的数字字符。

如果一个字符串 t 中至多有一对相邻字符是相等的，那么称这个字符串 t 是 半重复的 。例如，"0010" 、"002020" 、"0123" 、"2002" 和 "54944" 是半重复字符串，而 "00101022" （相邻的相同数字对是 00 和 22）和 "1101234883" （相邻的相同数字对是 11 和 88）不是半重复字符串。

请你返回 s 中最长 半重复 子字符串 的长度。

todo 给定一个断点，子区间是否有单调性，有的话就可以滑动窗口
 */

public class L2730 {
    public int longestSemiRepetitiveSubstring(String s) {
        int n = s.length();
        int max = 1;
        int[] f = new int[n]; //一定有一对
        int[] g = new int[n]; //一对也没有
        for (int i = n - 1; i > -1; i--) {
            if(i == n - 1){
                f[i] = 1;
                g[i] = 1;
            } else {
                //52233
                if(s.charAt(i) == s.charAt(i+1)) {
                    f[i] = 1 + g[i+1];
                    //todo 不是0！
                    g[i] = 1;
                } else {
                    f[i] = 1 + f[i+1];
                    g[i] = 1 + g[i+1];
                }
            }
            max = Math.max(max, f[i]);
            max = Math.max(max, g[i]);
        }
        return max;
    }
}
