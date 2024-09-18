package sulqn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是
回文串
 。返回 s 所有可能的分割方案。
 */

public class L131 {
    List<List<String>> ans = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(new L131().partition("cdd"));
    }

    public List<List<String>> partition(String s) {
        dfs(s, 0, "", new LinkedList<>());
        return ans;
    }

    private void dfs(String s, int cur, String curStr, LinkedList<String> a) {
        if (cur == s.length()) {
            if (curStr.length() > 0 && ispal(curStr)) {
                a.addLast(curStr);
                ans.add(new ArrayList<>(a));
                a.removeLast();

            }
        } else {
            if (curStr.length() > 0 && ispal(curStr)) {
                a.addLast(curStr);
                dfs(s, cur + 1, s.substring(cur, cur + 1), a);
                a.removeLast();
            }
            dfs(s, cur + 1, curStr + s.charAt(cur), a);

        }
    }

    public boolean ispal(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i <= j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }
}
