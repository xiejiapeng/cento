package linshen.backtrace;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。

返回所有可能的结果。答案可以按 任意顺序 返回。

提示：

1 <= s.length <= 25
s 由小写英文字母以及括号 '(' 和 ')' 组成
s 中至多含 20 个括号
 */

public class L301 {
    Set<String> ans = new HashSet<>();
    int min = Integer.MAX_VALUE;
    public List<String> removeInvalidParentheses(String s) {
        dfs(s, 0, 0, "");
        return new ArrayList<>(ans);
    }

    private void dfs(String s, int cur, int score, String l) {
        if(cur == s.length()) {
            if(score == 0) {
                if(s.length() - l.length() == min) {
                    ans.add(l);
                } else if(s.length() - l.length() < min) {
                    min = s.length() - l.length();
                    ans = new HashSet<>();
                    ans.add(l);
                }

            }
        } else {
            if(s.charAt(cur) == '(') {
                dfs(s, cur + 1, score + 1, l + "(");
                dfs(s, cur + 1, score, l);
            } else if(s.charAt(cur) == ')') {
                if(score > 0){
                    dfs(s, cur + 1, score - 1, l + ")");
                }
                dfs(s, cur + 1, score, l);
            } else {
                dfs(s, cur + 1, score, l + s.charAt(cur));
            }
        }
    }
}
