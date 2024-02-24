package sulwish;

import java.util.ArrayList;
import java.util.List;

/*
数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 */

public class L22 {
    List<String> ans = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        dfs(n, 0, 0, 0, "");
        return ans;
    }

    private void dfs(int n, int cur, int left, int right, String a) {
        if (cur == 2 * n) {
            if (left == right) ans.add(a);
            return;
        } else {
            if (left == right) {
                dfs(n, cur + 1, left + 1, right, a + "(");
            } else {
                dfs(n, cur + 1, left + 1, right, a + "(");
                dfs(n, cur + 1, left, right + 1, a + ")");
            }
        }
    }
}
