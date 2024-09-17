package sulwish;

/*
如果一个二进制字符串，是以一些 0（可能没有 0）后面跟着一些 1（也可能没有 1）的形式组成的，那么该字符串是 单调递增 的。

给你一个二进制字符串 s，你可以将任何 0 翻转为 1 或者将 1 翻转为 0 。

返回使 s 单调递增的最小翻转次数。
 */

public class L926 {
    public int minFlipsMonoIncr(String s) {
        int n = s.length();
        int[] sum = new int[n];
        sum[0] = s.charAt(0) - '0';
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + (s.charAt(i) - '0');
        }

        int[] ans = new int[n];
        ans[0] = 0;
        for (int i = 1; i < s.length(); i++) {
            ans[i] = Integer.MAX_VALUE;
            if (s.charAt(i) == '0') {
                ans[i] = Math.min(ans[i], sum[i]);
                ans[i] = Math.min(ans[i], 1 + ans[i - 1]);
            } else {
                ans[i] = Math.min(ans[i], ans[i - 1]);
            }
        }
        return ans[n - 1];
    }
}
