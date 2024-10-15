package index.seqdp;

/*
给定字符串列表 strs ，返回其中 最长的特殊序列 的长度。如果最长特殊序列不存在，返回 -1 。

特殊序列 定义如下：该序列为某字符串 独有的子序列（即不能是其他字符串的子序列）。

 s 的 子序列可以通过删去字符串 s 中的某些字符实现。

例如，"abc" 是 "aebdc" 的子序列，因为您可以删除"aebdc"中的下划线字符来得到 "abc" 。
"aebdc"的子序列还包括"aebdc"、 "aeb" 和 "" (空字符串)。

2 <= strs.length <= 50
1 <= strs[i].length <= 10
strs[i] 只包含小写英文字母
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class L522 {
    public int findLUSlength(String[] strs) {
        int n = strs.length;
        Map<String,Integer> cnt = new HashMap<>();
        int max = -1;
        for (String s : strs) {
            cnt.put(s, cnt.getOrDefault(s, 0) + 1);
        }
        for (String x : strs) {

            if(cnt.get(x) > 1)continue;
            else {
                boolean ok = true;
                for (String y : strs) {
                    if(x.equals(y))continue;
                    else {
                        if(sub(x, y))ok = false;
                    }
                }
                if(ok)max = Math.max(max, x.length());
            }
        }
        return max;
    }

    private boolean sub(String x, String y) {
        int i=0,j = 0;
        //should increase y as well
        for (; i < x.length(); i++,j++){
            while (j < y.length() && y.charAt(j) != x.charAt(i)) {
                j++;
            }
            if(j == y.length())return false;
        }
        return i == x.length();
    }

    public static void main(String[] args) {
        System.out.println(new L522().findLUSlength(new String[]{"aabbcc", "aabbcc","bc","bcc","aabbccc"}));
    }
}
