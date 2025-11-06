package linshen.dp;
/*
给你两个 版本号字符串 version1 和 version2 ，请你比较它们。版本号由被点 '.' 分开的修订号组成。修订号的值 是它 转换为整数 并忽略前导零。

比较版本号时，请按 从左到右的顺序 依次比较它们的修订号。如果其中一个版本字符串的修订号较少，则将缺失的修订号视为 0。

返回规则如下：

如果 version1 < version2 返回 -1，
如果 version1 > version2 返回 1，
除此之外返回 0。
提示：

1 <= version1.length, version2.length <= 500
version1 和 version2 仅包含数字和 '.'
version1 和 version2 都是 有效版本号
version1 和 version2 的所有修订号都可以存储在 32 位整数 中
 */
public class LCR165 {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int n = v1.length;
        int m = v2.length;
        for (int i = 0; i < Math.max(n, m); i++) {
            String s1 = i < n ? v1[i] : "0";
            String s2 = i < m ? v2[i] : "0";
            if(Integer.valueOf(s1) < Integer.valueOf(s2))return -1;
            else if(Integer.valueOf(s1) > Integer.valueOf(s2))return 1;
        }
        return 0;
    }
}
