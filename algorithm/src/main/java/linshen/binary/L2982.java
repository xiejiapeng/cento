package linshen.binary;

/*
给你一个仅由小写英文字母组成的字符串 s 。

如果一个字符串仅由单一字符组成，那么它被称为 特殊 字符串。例如，字符串 "abc" 不是特殊字符串，而字符串 "ddd"、"zz" 和 "f" 是特殊字符串。

返回在 s 中出现 至少三次 的 最长特殊子字符串 的长度，如果不存在出现至少三次的特殊子字符串，则返回 -1 。

子字符串 是字符串中的一个连续 非空 字符序列。
 */

import java.util.HashMap;
import java.util.Map;
//todo h: 复杂度没问题

public class L2982 {
    public int maximumLength(String s) {
        char ch;
        int n = s.length();
        Map<Character, Integer> cnt = new HashMap<>();
        for (char c : s.toCharArray()) {
            cnt.put(c, cnt.getOrDefault(c, 0) + 1);
        }
        int max = -1;
        for (int i = 0; i < 26; i++){
            ch = (char)('a' + i);
            if(!cnt.containsKey(ch))continue;
            int m = findMax(s, ch, 1, cnt.get(ch));
            max = Math.max(max, m);
        }
        return max;
    }

    private int findMax(String s, char ch, int left, int right) {
        if(left > right) return -1;
        if(left == right) {
            if(check(s, ch, right))return right;
            else return -1;
        } else if(left == right - 1) {
            if(check(s, ch, right))return right;
            else if(check(s, ch, left))return left;
            else return -1;
        } else {
            int mid = (left + right) / 2;
            if(check(s, ch, mid))return findMax(s, ch, mid, right);
            else return findMax(s, ch, left, mid - 1);
        }
    }

    private boolean check(String s, char ch, int len) {
        int cnt = 0;
        int ans = 0;
        for (int left = 0, right = 0; right < s.length(); right++){
            if(s.charAt(right) == ch)cnt++;
            if(right - left + 1 > len){
                if(s.charAt(left) == ch)cnt--;
                left++;
            }
            if(cnt == len)ans++;
            if(ans >= 3)return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new L2982().maximumLength("abc"));
    }
}
