package sulwish;

import java.util.HashMap;
import java.util.Map;

public class L3 {
    public static void main(String[] args) {
        String s = "abba";
        System.out.println(new L3().lengthOfLongestSubstring(s));
    }

    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        if (len <= 0) return 0;
        Map<Character, Integer> exist = new HashMap<>();
        int max = 1;
        for (int i = 0, j = 0; j < len; j++) {
            char c = s.charAt(j);
            while (exist.containsKey(c)) {
                char remove = s.charAt(i);
                exist.remove(remove);
                i++;
            }
            max = Math.max(max, j - i + 1);
            exist.put(c, j);
        }
        return max;
    }
}
