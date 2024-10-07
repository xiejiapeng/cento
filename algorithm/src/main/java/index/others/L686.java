package index.others;

/*
给定两个字符串 a 和 b，寻找重复叠加字符串 a 的最小次数，使得字符串 b 成为叠加后的字符串 a 的子串，如果不存在则返回 -1。

注意：字符串 "abc" 重复叠加 0 次是 ""，重复叠加 1 次是 "abc"，重复叠加 2 次是 "abcabc"。
 */

public class L686 {
    public static void main(String[] args) {
        System.out.println(new L686().repeatedStringMatch("abcd", "bc"));
    }

    public int repeatedStringMatch(String a, String b) {
        int min = Integer.MAX_VALUE;
        if (a.startsWith(b)) return 1;
        for (int i = 0; i < a.length(); i++) {
            String prefix = a.substring(i);
            if (prefix.startsWith(b)) return 1;
            if (!b.startsWith(prefix)) continue;
            if (prefix.equals(b)) min = Math.min(min, 1);
            else {
                int cnt = 1;
                while (!prefix.startsWith(b)) {
                    prefix = prefix + a;
                    cnt++;
                    if (prefix.length() > b.length()) break;
                }
                if (prefix.startsWith(b)) min = Math.min(min, cnt);

            }
        }
        if (min == Integer.MAX_VALUE) return -1;
        else return min;
    }
}
