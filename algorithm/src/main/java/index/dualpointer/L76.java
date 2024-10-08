package index.dualpointer;

/*
给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。
如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。

注意：

对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
如果 s 中存在这样的子串，我们保证它是唯一的答案。
m == s.length
n == t.length
1 <= m, n <= 105
s 和 t 由英文字母组成

 */

import java.util.HashMap;
import java.util.Map;

public class L76 {
    public String minWindow(String s, String t) {
        Map<Character,Integer> cur = new HashMap<>();
        Map<Character,Integer> ts = convert(t);
        String ans = "";
        for (int i = 0, j = 0; i < s.length(); i++){
            cur.put(s.charAt(i), cur.getOrDefault(s.charAt(i), 0) + 1);
            while (match(cur, ts)) {
                if(ans.isEmpty() || i-j+1<ans.length()) {
                    ans = s.substring(j,i+1);
                }
                if(j < i) {
                    cur.put(s.charAt(j), cur.get(s.charAt(j))-1);
                    j++;
                } else {
                    break;
                }
            }
        }
        return ans;
    }

    private Map<Character,Integer> convert(String s) {
        Map<Character, Integer> a = new HashMap<>();
        for (char c : s.toCharArray()) {
            a.put(c, a.getOrDefault(c, 0) + 1);
        }
        return a;
    }

    private boolean match(Map<Character,Integer> a, Map<Character,Integer> b) {
        for(Character c : b.keySet()) {
            if(!a.containsKey(c) || a.get(c) < b.get(c))return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new L76().minWindow("a","a"));
    }
}
