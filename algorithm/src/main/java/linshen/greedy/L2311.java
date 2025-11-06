package linshen.greedy;

/*
给你一个二进制字符串 s 和一个正整数 k 。

请你返回 s 的 最长 子序列的长度，且该子序列对应的 二进制 数字小于等于 k 。

注意：

子序列可以有 前导 0 。
空字符串视为 0 。
子序列 是指从一个字符串中删除零个或者多个字符后，不改变顺序得到的剩余字符序列。
1001010
    101
 */

public class L2311 {
    //todo hhhhh 未能解决
    public int longestSubsequence(String s, int k) {
        int sm = 0;
        int cnt = 0;
        int bits = (int) (Math.log(k) / Math.log(2)) + 1;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(s.length() - 1 - i);
            if (ch == '1') {
                if (i < bits && sm + (1 << i) <= k) {
                    sm += 1 << i;
                    cnt++;
                }
            } else {
                cnt++;
            }
        }
        return cnt;
    }


    public static void main(String[] args) {
        System.out.println(new L2311().longestSubsequence("001010101011010100010101101010010", 93951055));
    }
}
