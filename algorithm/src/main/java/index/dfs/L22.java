package index.dfs;

import java.util.ArrayList;
import java.util.List;

/*
数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。


 */

public class L22 {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        dfs(n, 0, 0, 0, "", ans);
        return ans;
    }

    private void dfs(int n, int id, int left, int right, String cur, List<String> ans) {
        if (id == 2 * n) {
            if (left == right) {
                ans.add(cur);
            }
        } else {
            if (left > right) {
                dfs(n, id + 1, left, right + 1, cur + ")", ans);
            }
            dfs(n, id + 1, left + 1, right, cur + "(", ans);
        }
    }
}
