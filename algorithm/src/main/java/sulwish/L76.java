package sulwish;

import java.util.HashMap;
import java.util.Map;

public class L76 {
    public static void main(String[] args) {
        System.out.println(new L76().minWindow("EBANC", "ABC"));
    }

    public String minWindow(String s, String t) {
        Map<Character, Integer> tall = all(t);
        Map<Character, Integer> sall = new HashMap<>();
        int min = Integer.MAX_VALUE;
        String ans = "";
        for (int i = 0, j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            sall.put(c, sall.getOrDefault(c, 0) + 1);
            while (cover(sall, tall)) {
//                System.out.println(s.substring(i, j+1));
                if (j - i + 1 < min) {
                    min = j - i + 1;
                    ans = s.substring(i, j + 1);
                }
                char ci = s.charAt(i);
                int count = sall.get(ci);
                if (count == 1) sall.remove(ci);
                else sall.put(ci, count - 1);

                i++;
            }
        }
        return ans;
    }

    public Map<Character, Integer> all(String s) {
        Map<Character, Integer> all = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            all.put(s.charAt(i), all.getOrDefault(s.charAt(i), 0) + 1);
        }
        return all;
    }

    public boolean cover(Map<Character, Integer> a, Map<Character, Integer> b) {
        for (Character c : b.keySet()) {
            if (!a.containsKey(c)) return false;
            else if (a.get(c) < b.get(c)) return false;
        }
        return true;
    }
}
