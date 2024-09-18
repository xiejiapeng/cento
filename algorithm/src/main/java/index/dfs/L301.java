package index.dfs;

import java.util.*;

public class L301 {
    public static void main(String[] args) {
        L301 l = new L301();
        System.out.println(l.removeInvalidParentheses(")("));
    }

    public List<String> removeInvalidParentheses(String s) {
        Map<Integer, Set<String>> ans = new HashMap<>();
        dfs(s, 0, "", ans, 0, 0);
        int min = ans.keySet().stream().mapToInt(i -> i).min().getAsInt();
        return new ArrayList<>(ans.get(min));
    }

    private void dfs(String s, int id, String cur, Map<Integer, Set<String>> ans, int left, int right) {
        if (id == s.length()) {
            if (left == right) {
                Set<String> x = ans.getOrDefault(s.length() - cur.length(), new HashSet<>());
                x.add(cur);
                ans.put(s.length() - cur.length(), x);
            }
        } else {
            if (s.charAt(id) != '(' && s.charAt(id) != ')') {
                dfs(s, id + 1, cur + (s.charAt(id)), ans, left, right);
            } else {
                if (s.charAt(id) == '(') {
                    //delete
                    dfs(s, id + 1, cur, ans, left, right);
                    //add
                    dfs(s, id + 1, cur + "(", ans, left + 1, right);
                } else {
                    //delete
                    dfs(s, id + 1, cur, ans, left, right);
                    //add
                    if (right < left) {
                        dfs(s, id + 1, cur + ")", ans, left, right + 1);
                    }
                }
            }
        }
    }
}
