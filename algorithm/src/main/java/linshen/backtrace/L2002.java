package linshen.backtrace;

/*
给你一个字符串 s ，请你找到 s 中两个 不相交回文子序列 ，使得它们长度的 乘积最大 。
两个子序列在原字符串中如果没有任何相同下标的字符，则它们是 不相交 的。

请你返回两个回文子序列长度可以达到的 最大乘积 。

子序列 指的是从原字符串中删除若干个字符（可以一个也不删除）后，剩余字符不改变顺序而得到的结果。
如果一个字符串从前往后读和从后往前读一模一样，那么这个字符串是一个 回文字符串 。
提示：

2 <= s.length <= 12
s 只含有小写英文字母。
 */

import java.util.LinkedList;

public class L2002 {
    int max = -1;
    public int maxProduct(String s) {
        dfs(s.toCharArray(), 0, "", "");
        return max;
    }

    private void dfs(char[] s, int cur, String left, String right) {
        if(cur == s.length) {
            if(isP(left) && isP(right)) {
                max = Math.max(max, left.length() * right.length());
            }
        } else {
            //to left
            dfs(s, cur + 1, left + s[cur], right);

            //to right
            dfs(s, cur + 1, left, right + s[cur]);

            dfs(s, cur + 1, left, right);
        }
    }
    private boolean isP(String s){
        int l = 0;
        int r = s.length() - 1;
        while (l <= r) {
            if(s.charAt(l) != s.charAt(r))return false;
            l++;
            r--;
        }
        return true;
    }
}
