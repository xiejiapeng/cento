package sulwish;

import java.util.ArrayList;
import java.util.List;

/*
给定一个字符串 s ，通过将字符串 s 中的每个字母转变大小写，我们可以获得一个新的字符串。

返回 所有可能得到的字符串集合 。以 任意顺序 返回输出。
 */

public class L784 {
    List<String> ans = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(new L784().letterCasePermutation("a1"));
    }

    public List<String> letterCasePermutation(String s) {
        dfs(s, 0, "");
        return ans;
    }

    private void dfs(String s, int cur, String sb) {
        if (cur == s.length()) {
            ans.add(sb);
        } else {
            if (s.charAt(cur) >= 'a' && s.charAt(cur) <= 'z') {
                char c = (char) ('A' + s.charAt(cur) - 'a');
                dfs(s, cur + 1, sb + c);
                dfs(s, cur + 1, sb + s.charAt(cur));
            } else if (s.charAt(cur) >= 'A' && s.charAt(cur) <= 'Z') {
                char c = (char) ('a' + s.charAt(cur) - 'A');
                dfs(s, cur + 1, sb + c);
                dfs(s, cur + 1, sb + s.charAt(cur));
            } else {
                dfs(s, cur + 1, sb + s.charAt(cur));
            }
        }
    }


}
