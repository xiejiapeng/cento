package sulqn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。

返回所有可能的结果。答案可以按 任意顺序 返回。
 */

public class L301 {
    Set<String> ans = new HashSet<>();
    int maxLen = -1;

    public List<String> removeInvalidParentheses(String s) {
        dfs(s, 0, 0, "");
        return new ArrayList<>(ans);
    }

    private void dfs(String s, int cur, int score, String curStr) {
        if (cur == s.length()) {
            if (score == 0) {
                if (curStr.length() > maxLen) {
                    maxLen = curStr.length();
                    ans.clear();
                    ;
                    ans.add(curStr);
                } else if (curStr.length() == maxLen) {
                    ans.add(curStr);
                }
            }
        } else {
            //algebra
            if (s.charAt(cur) == '(') {
                //add
                dfs(s, cur + 1, score + 1, curStr + "(");
                //skip
                dfs(s, cur + 1, score, curStr);
            } else if (s.charAt(cur) == ')') {
                //add
                if (score > 0) dfs(s, cur + 1, score - 1, curStr + ')');
                //skip
                dfs(s, cur + 1, score, curStr);
            } else {
                dfs(s, cur + 1, score, curStr + s.charAt(cur));
            }
        }
    }

}
