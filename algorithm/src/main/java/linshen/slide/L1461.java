package linshen.slide;

/*
给你一个二进制字符串 s 和一个整数 k 。如果所有长度为 k 的二进制字符串都是 s 的子串，
请返回 true ，否则请返回 false 。
 */

import java.util.HashSet;
import java.util.Set;

public class L1461 {
    public boolean hasAllCodes(String s, int k) {
        Set<String> all = new HashSet<>();
        String cur = "";
        for (int right = 0; right < s.length(); right++) {
            cur = cur + s.charAt(right);
            int left = right - k + 1;
            if(left - 1 >= 0) {
                cur = cur.substring(1);
            }
            if(left >= 0) {
                all.add(cur);
            }
        }
        return all.size() == (int) (Math.pow(2, k));
    }
}
