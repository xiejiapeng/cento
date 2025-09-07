package linshen.greedy;

/*
当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。

给定一个整数 n ，返回 小于或等于 n 的最大数字，且数字呈 单调递增 。
223331 => 222221

3331 => 2999

1110 => 0999
 */

public class L738 {
    public static void main(String[] args) {
        System.out.println(new L738().monotoneIncreasingDigits(10));
    }

    public int monotoneIncreasingDigits(int n) {
        String s = String.valueOf(n);
        int last = -1;
        int stop = s.length();
        for (int i = 0; i < s.length(); i++) {
            if (last == -1 || s.charAt(i) > s.charAt(last)) {
                last = i;
            } else if (s.charAt(i) < s.charAt(last)) {
                stop = i;
                break;
            }
        }
        if (stop == s.length()) return n;
        else {
            if (last == 0) {
                if (s.charAt(last) == '0') {
                    int t = 0;
                    for (int i = 0; i < s.length() - 1; i++) {
                        t = t * 10 + 9;
                    }
                    return t;
                } else {
                    int t = s.charAt(last) - '1';
                    for (int i = 1; i < s.length(); i++) {
                        t = t * 10 + 9;
                    }
                    return t;
                }
            } else {
                int t = 0;
                for (int i = 0; i < s.length(); i++) {
                    int u;
                    if (i == last) u = (s.charAt(i) - '1');
                    else if (i > last) u = 9;
                    else u = s.charAt(i) - '0';
                    t = t * 10 + u;
                }
                return t;
            }
        }
    }
}
