package linshen.structure.stack;

/*
一个括号字符串是只由 '(' 和 ')' 组成的 非空 字符串。如果一个字符串满足下面 任意 一个条件，那么它就是有效的：

字符串为 ().
它可以表示为 AB（A 与 B 连接），其中A 和 B 都是有效括号字符串。
它可以表示为 (A) ，其中 A 是一个有效括号字符串。
给你一个括号字符串 s 和一个字符串 locked ，两者长度都为 n 。locked 是一个二进制字符串，只包含 '0' 和 '1' 。对于 locked 中 每一个 下标 i ：

如果 locked[i] 是 '1' ，你 不能 改变 s[i] 。
如果 locked[i] 是 '0' ，你 可以 将 s[i] 变为 '(' 或者 ')' 。
如果你可以将 s 变为有效括号字符串，请你返回 true ，否则返回 false 。
 */

public class L2116 {
    public boolean canBeValid(String s, String locked) {
        int n = s.length();
        if(n % 2 != 0)return false;
        int min = 0;
        int max = 0;
        for (int i = 0; i < n; i++){
            if(locked.charAt(i) == '1') {
                if(s.charAt(i) == '('){
                    min -= 1;
                    max -= 1;
                } else {
                    min += 1;
                    max += 1;
                }
            } else {
                if(s.charAt(i) == '(') {
                    max += 2;
                } else {
                    min -= 2;
                }
            }
            if(min > 0)return false;
        }
        return min <= 0 && max >= 0;
    }

    class Solution {
        //todo hhhhhh 记住最小前缀法！！
        public boolean canBeValid2(String s, String locked) {
            int n = s.length();
            int mx = 0;   // 可以达到的最大分数
            int mn = 0;   // 可以达到的最小分数 与 最小有效前缀对应分数 的较大值
            for (int i = 0; i < n; ++i) {
                if (locked.charAt(i) == '1') {
                    // 此时对应字符无法更改
                    int diff;
                    if (s.charAt(i) == '(') {
                        diff = 1;
                    } else {
                        diff = -1;
                    }
                    mx += diff;
                    mn = Math.max(mn + diff, (i + 1) % 2);
                } else {
                    // 此时对应字符可以更改
                    ++mx;
                    mn = Math.max(mn - 1, (i + 1) % 2);
                }
                if (mx < mn) {
                    // 此时该前缀无法变为有效前缀
                    return false;
                }
            }
            // 最终确定 s 能否通过变换使得分数为 0（成为有效字符串）
            return mn == 0;
        }
    }



    public static void main(String[] args) {
        System.out.println(new L2116().canBeValid("))()))", "010100"));
    }
}
