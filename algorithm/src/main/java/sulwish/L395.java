package sulwish;

/*
给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。

如果不存在这样的子字符串，则返回 0。
 */
public class L395 {
    public static void main(String[] args) {
        System.out.println(new L395().longestSubstring("aaabbbcdefcdefgggggggggggggggcde", 3));
    }

    public int longestSubstring(String s, int k) {
        //强行要求子串中不同的字符为固定值

        if (s == null || s.length() <= 0) return 0;
        int maxLen = 0;

        int len = s.length();
        for (int cnt = 1; cnt <= 26; cnt++) {

            if (cnt == 1) {
                System.out.println("x");
            }


            int cur = 0;
            int[] curC = new int[26];

            for (int left = 0, right = 0; right < len; right++) {
                char c = s.charAt(right);
                if (curC[c - 'a'] == 0) cur++;
                curC[c - 'a']++;

                while (left <= right && cur > cnt) {
                    if (curC[s.charAt(left) - 'a'] == 1) cur--;
                    curC[s.charAt(left) - 'a']--;
                    left++;
                }

                if (cur == cnt) {
                    boolean all = true;
                    for (int i = 0; i < 26; i++) {
                        if (curC[i] > 0 && curC[i] < k) {
                            all = false;
                            break;
                        }
                    }
                    if (all) {
                        maxLen = Math.max(maxLen, right - left + 1);
                    }
                }
            }
        }

        return maxLen;
    }
}
