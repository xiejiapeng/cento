package linshen.zhizhen;

/*
给你一个 二进制 字符串 s 和一个整数 k。

如果一个 二进制字符串 满足以下任一条件，则认为该字符串满足 k 约束：

字符串中 0 的数量最多为 k。
字符串中 1 的数量最多为 k。
返回一个整数，表示 s 的所有满足 k 约束 的子字符串的数量。
 */

public class L3258 {
    public int countKConstraintSubstrings(String s, int k) {
        int n = s.length();
        int c0 = 0;
        int c1 = 0;
        int ans = 0;
        for (int left = 0, right = 0; right < n; right++){
            if(s.charAt(right) == '1')c1++;
            else c0++;

            while (left < right && (c0 > k) && (c1 > k)) {
                if(s.charAt(left) == '0')c0--;
                else c1--;
                left++;
            }
            ans += (right - left + 1);
        }
        return ans;
    }
}
