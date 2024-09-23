package index.binaryenumerate;

import java.util.ArrayList;
import java.util.List;

/*
给定一个字符串 s ，通过将字符串 s 中的每个字母转变大小写，我们可以获得一个新的字符串。

返回 所有可能得到的字符串集合 。以 任意顺序 返回输出。
 */

public class L784 {
    List<String> ans = new ArrayList<>();

    public List<String> letterCasePermutation(String s) {
        dfs(s, "", 0);
        return ans;
    }

    private void dfs(String s, String cur, int id) {
        if (id == s.length()) {
            ans.add(cur);
        } else {
            if (s.charAt(id) >= 'a' && s.charAt(id) <= 'z') {
                dfs(s, cur + s.charAt(id), id + 1);
                char c = (char) ('A' + s.charAt(id) - 'a');
                dfs(s, cur + c, id + 1);
            } else if (s.charAt(id) >= 'A' && s.charAt(id) <= 'Z') {
                dfs(s, cur + s.charAt(id), id + 1);
                char c = (char) ('a' + s.charAt(id) - 'A');
                dfs(s, cur + c, id + 1);
            } else {
                dfs(s, cur + s.charAt(id), id + 1);
            }
        }
    }
}
