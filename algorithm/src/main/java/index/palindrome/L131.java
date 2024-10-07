package index.palindrome;

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
        System.out.println(new L131().partition("aab"));
    }

    public List<List<String>> partition(String s) {
        int n = s.length();
        boolean[][] is = new boolean[n][n];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    is[i][j] = true;
                } else if (len == 2) {
                    is[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    is[i][j] = (s.charAt(i) == s.charAt(j)) && is[i + 1][j - 1];
                }
            }
        }

        dfs(s, is, 0, 0, new LinkedList<>());
        return ans;
    }

    private void dfs(String s, boolean[][] is, int i, int start, LinkedList<String> as) {
        if (i == s.length()) {
            if (i - 1 >= start && is[start][i - 1]) {
                as.addLast(s.substring(start));
                List<String> a = new ArrayList<>(as);
                ans.add(a);
                as.removeLast();
            }
        } else {
            //add into
            dfs(s, is, i + 1, start, as);
            //truncate
            if (i - 1 >= start && is[start][i - 1]) {
                as.addLast(s.substring(start, i));
                dfs(s, is, i + 1, i, as);
                as.removeLast();
            }
        }
    }
}
