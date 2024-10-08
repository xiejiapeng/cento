package index.dualpointer;

/*
给你一个字符串 s 和一个字符串数组 dictionary ，找出并返回 dictionary 中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。

如果答案不止一个，返回长度最长且字母序最小的字符串。如果答案不存在，则返回空字符串。
 */

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class L524 {
    public String findLongestWord(String s, List<String> dictionary) {
        Collections.sort(dictionary, (o1, o2) -> {
            if(o1.length() != o2.length()){
                return Integer.compare(o2.length(), o1.length());
            } else {
                return o1.compareTo(o2);
            }
        });
        for (String x : dictionary){
            if(can(s, x))return x;
        }
        return "";
    }

    private boolean can(String a, String b) {
        int left = 0;
        int right = 0;
        while (right < b.length()) {
            if(left >= a.length())return false;
            if(a.charAt(left) == b.charAt(right)){
                left++;
                right++;
            } else {
                left++;
            }
        }
        return true;
    }
}
