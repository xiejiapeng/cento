package linshen.zhizhen;

/*
如果字符串中的所有字符都相同，那么这个字符串是单字符重复的字符串。

给你一个字符串 text，你只能交换其中两个字符一次或者什么都不做，然后得到一些单字符重复的子串。返回其中最长的子串的长度。
 */

import scala.Char;

import java.util.*;
import java.util.stream.Collectors;

public class L1156 {
    //todo h: 非常有意思的一道题，值得关注；特别是satisfy方法的实现
    public int maxRepOpt1(String text) {
        int n = text.length();
        Map<Character, Integer> l = new HashMap<>();
        Map<Character, Integer> r = new HashMap<>();
        Map<Character, Integer> m = new HashMap<>();
        for (char c : text.toCharArray()) {
            r.put(c, r.getOrDefault(c, 0) + 1);
        }
        int ans = Integer.MIN_VALUE;
        for (int left = 0, right = 0; right < n; right++){
            char rc = text.charAt(right);
            m.put(rc, m.getOrDefault(rc, 0) + 1);
            r.put(rc, r.get(rc) - 1);
            if(r.get(rc) == 0)r.remove(rc);
            while (!satisfy(m,l,r)) {
                char lc = text.charAt(left);
                m.put(lc, m.get(lc) - 1);
                if(m.get(lc) == 0)m.remove(lc);
                l.put(lc, l.getOrDefault(lc, 0) + 1);
                left++;
            }
            ans = Math.max(ans, m.values().stream().mapToInt(i -> i).sum());
        }
        return ans;
    }

    private boolean satisfy(Map<Character, Integer> a, Map<Character, Integer> l, Map<Character, Integer> r) {
        if(a.keySet().size() > 2)return false;
        else if(a.keySet().size() == 2) {
            List<Character> ks = new ArrayList<>(a.keySet());
            char x = ks.get(0);
            char y = ks.get(1);
            boolean can = false;
            //all to x
            can |= (a.get(y) == 1 && (l.containsKey(x) || r.containsKey(x)));
            //all to y
            can |= (a.get(x) == 1 && (l.containsKey(y) || r.containsKey(y)));
            return can;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        System.out.println(new L1156().maxRepOpt1("aaabaaa"));
    }
}
