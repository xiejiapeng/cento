package linshen.structure;

/*
给你一个小写英文字母组成的字符串 s 和一个二维整数数组 shifts ，其中 shifts[i] = [starti, endi, directioni] 。
对于每个 i ，将 s 中从下标 starti 到下标 endi （两者都包含）所有字符都进行移位运算，如果 directioni = 1 将字符向后移位，如果 directioni = 0 将字符向前移位。

将一个字符 向后 移位的意思是将这个字符用字母表中 下一个 字母替换（字母表视为环绕的，所以 'z' 变成 'a'）。类似的，
将一个字符 向前 移位的意思是将这个字符用字母表中 前一个 字母替换（字母表是环绕的，所以 'a' 变成 'z' ）。

请你返回对 s 进行所有移位操作以后得到的最终字符串。
 */

public class L2381 {
    public static void main(String[] args) {
        char t = 'a';
        int x = -28;
        char c = (char) ('a' + (t - 'a' + x + 26) % 26);
        System.out.println(c);
    }

    public String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        long[] diff = new long[n + 1];
        for (int[] shift : shifts) {
            int start = shift[0];
            int end = shift[1];
            int dir = shift[2] == 1 ? 1 : -1;
            diff[start] += dir;
            diff[end + 1] -= dir;
        }
        StringBuilder sb = new StringBuilder();
        int x = 0;
        for (int i = 0; i < n; i++) {
            //todo hhh 注意这里处理字母移位的方式
            x += diff[i];
            int y = (x % 26 + 26) % 26;
            char c = (char) ('a' + (s.charAt(i) - 'a' + y + 26) % 26);
            sb.append(c);
        }
        return sb.toString();
    }
}
