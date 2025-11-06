package linshen.structure;

/*
如果某个字符串中 至多一个 字母出现 奇数 次，则称其为 最美 字符串。

例如，"ccjjc" 和 "abab" 都是最美字符串，但 "ab" 不是。
给你一个字符串 word ，该字符串由前十个小写英文字母组成（'a' 到 'j'）。请你返回 word 中 最美非空子字符串 的数目。如果同样的子字符串在 word 中出现多次，那么应当对 每次出现 分别计数。

子字符串 是字符串中的一个连续字符序列。
 */

import java.util.HashMap;
import java.util.Map;

public class L1915 {
    public long wonderfulSubstrings(String word) {
        int n = word.length();
        Map<Integer, Long> cnt = new HashMap<>();
        int cur = 0;
        cnt.put(cur, 1L);
        long ans = 0;
        for (int i = 0; i < word.length(); i++){
            int t = word.charAt(i) - 'a';
            cur = cur ^ (1 << t);
            //all even
            ans += cnt.getOrDefault(cur, 0L);
            //one odd
            for (int j = 0; j < 10; j++){
                int target = cur ^ (1 << j);
                ans += cnt.getOrDefault(target, 0L);
            }
            cnt.put(cur, cnt.getOrDefault(cur, 0L) + 1);
        }
        return ans;
    }
}
