package linshen.zhizhen;

/*
给你字符串 s 和整数 k 。
请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。

英文中的 元音字母 为（a, e, i, o, u）。
 */

import scala.Char;

import java.util.HashMap;
import java.util.Map;

public class L1456 {
    public int maxVowels(String s, int k) {
        if(s == null)return 0;
        int max = 0;
        Map<Character, Integer> all = new HashMap<>();
        all.put('a', 0);
        all.put('e', 0);
        all.put('i', 0);
        all.put('o', 0);
        all.put('u', 0);
        int len = s.length();
        //todo h: 就从0开始，虽然0作为了初始值，但没有影响初始状态，状态都是由right更新的，此时right还没有初始化
        int left = 0;
        for(int right = 0; right < len; right++) {
            Character c = s.charAt(right);
            if(all.containsKey(c)) {
                all.put(c, all.get(c) + 1);
            }
            while (right - left + 1 > k) {
                if(left >= 0) {
                    Character rmv = s.charAt(left);
                    if(all.containsKey(rmv)) {
                        all.put(rmv, all.get(rmv) - 1);
                    }
                }
                left++;
            }
            int sum = all.values().stream().mapToInt(i -> i).sum();
            max = Math.max(max, sum);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new L1456().maxVowels("aeioxuae", 3));
    }
}
