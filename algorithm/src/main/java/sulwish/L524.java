package sulwish;

import java.util.Collections;
import java.util.List;

/*
给你一个字符串 s 和一个字符串数组 dictionary ，找出并返回 dictionary 中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。

如果答案不止一个，返回长度最长且字母序最小的字符串。如果答案不存在，则返回空字符串。
 */

public class L524 {
    public String findLongestWord(String s, List<String> dictionary) {
        Collections.sort(dictionary);
        int maxlen = 0;
        String ans = "";
        for (String d : dictionary){
            if(isSubstr(s, d)){
                if(d.length() > maxlen) {
                    maxlen = d.length();
                    ans = d;
                }
            }
        }
        return ans;
    }

    public boolean isSubstr(String a, String b) {
        int ai = 0;
        int bi = 0;
        while (ai < a.length() && bi < b.length()) {
            if(a.charAt(ai) == b.charAt(bi)){
                ai++;
                bi++;
            } else {
                ai++;
            }
        }
        return bi == b.length();
    }
}
