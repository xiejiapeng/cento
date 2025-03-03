package linshen.zhizhen;

/*
一个整数 num 的 k 美丽值定义为 num 中符合以下条件的 子字符串 数目：

子字符串长度为 k 。
子字符串能整除 num 。
给你整数 num 和 k ，请你返回 num 的 k 美丽值。

注意：

允许有 前缀 0 。
0 不能整除任何值。
一个 子字符串 是一个字符串里的连续一段字符序列。
 */

public class L2269 {
    public int divisorSubstrings(int num, int k) {
        String s = String.valueOf(num);
        int n = s.length();
        int cur = 0;
        int ans = 0;
        for (int right = 0; right < n; right++){
            int left = right - k + 1;
            if(left - 1 >= 0) {
                int t = (s.charAt(left - 1) - '0') * (int)Math.pow(10, k-1);
                cur -= t;
            }
            cur = cur * 10 + (s.charAt(right) - '0');
            if(left >= 0) {
                if(cur != 0 && num % cur == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
