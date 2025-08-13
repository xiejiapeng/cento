package linshen.structure.heap;

/*
给定两个整数 a 和 b ，返回 任意 字符串 s ，要求满足：

s 的长度为 a + b，且正好包含 a 个 'a' 字母与 b 个 'b' 字母；
子串 'aaa' 没有出现在 s 中；
子串 'bbb' 没有出现在 s 中。
提示：
0 <= a, b <= 100
对于给定的 a 和 b，保证存在满足要求的 s
 */

public class L984 {
    public String strWithout3a3b(int a, int b) {
        //todo h 尽量不要让多的遗留到最后
        StringBuilder sb = new StringBuilder();
        while (a > 0 || b > 0) {
            if(a >= b) {
                if(sb.length() >= 2 && sb.substring(sb.length()-2).equals("aa")){
                    sb.append('b');
                    b--;
                } else {
                    sb.append('a');
                    a--;
                }
            } else {
                if(sb.length() >= 2 && sb.substring(sb.length()-2).equals("bb")){
                    sb.append('a');
                    a--;
                } else {
                    sb.append('b');
                    b--;
                }
            }
        }
        return sb.toString();
    }
}
