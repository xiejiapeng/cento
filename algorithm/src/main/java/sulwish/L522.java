package sulwish;

/*
给定字符串列表 strs ，返回其中 最长的特殊序列 的长度。如果最长特殊序列不存在，返回 -1 。

特殊序列 定义如下：该序列为某字符串 独有的子序列（即不能是其他字符串的子序列）。

 s 的 子序列可以通过删去字符串 s 中的某些字符实现。

例如，"abc" 是 "aebdc" 的子序列，因为您可以删除"aebdc"中的下划线字符来得到 "abc" 。"aebdc"的子序列还包括"aebdc"、 "aeb" 和 "" (空字符串)。
 */

public class L522 {
    public int findLUSlength(String[] strs) {
        int n = strs.length;
        int max = -1;
        for (int i = 0; i < n; i++) {
            boolean find = true;
            for (int j = 0; j < n; j++) {
                if (j == i) continue;
                else {
                    String x = strs[i];
                    String y = strs[j];
                    if (isSubseq(x, y)) find = false;
                }
            }
            if (find) {
                max = Math.max(max, strs[i].length());
            }
        }
        return max;
    }

    private boolean isSubseq(String x, String y) {
        int i = 0;
        for (int j = 0; j < y.length(); j++) {
            if (i == x.length()) return true;
            if (y.charAt(j) == x.charAt(i)) {
                i++;
            }
        }
        return i == x.length();
    }
}
