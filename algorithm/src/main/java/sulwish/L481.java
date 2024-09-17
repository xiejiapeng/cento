package sulwish;

/*
神奇字符串 s 仅由 '1' 和 '2' 组成，并需要遵守下面的规则：

神奇字符串 s 的神奇之处在于，串联字符串中 '1' 和 '2' 的连续出现次数可以生成该字符串。
s 的前几个元素是 s = "1221121221221121122……" 。如果将 s 中连续的若干 1 和 2 进行分组，可以得到 "1 22 11 2 1 22 1 22 11 2 11 22 ......" 。每组中 1 或者 2 的出现次数分别是 "1 2 2 1 1 2 1 2 2 1 2 2 ......" 。上面的出现次数正是 s 自身。

给你一个整数 n ，返回在神奇字符串 s 的前 n 个数字中 1 的数目。
 */

public class L481 {
    static int N = 100010;
    static int[] f = new int[N];
    static {
        StringBuilder sb = new StringBuilder();
        sb.append("01"); // 首位多加一个 0 作为哨兵
        for (int i = 1, j = 1, cnt = 0; i < N; j++) {
            int last = sb.charAt(sb.length() - 1) - '0', t = sb.charAt(j) - '0';
            if (last == 1) {
                if (t == 1) {
                    // 当原串当前字符是 1，而计数串当前字符为 1
                    // 往后构造形成的原串只能是 12，原串指针后移一位
                    sb.append("2");
                    f[i] = ++cnt; i++;
                } else {
                    // 当原串当前字符是 1，而计数串当前字符为 2
                    // 往后构造形成的原串只能是 112，此时同步更新 f[i + 1]，原串指针后移两位
                    sb.append("12");
                    f[i] = ++cnt; f[i + 1] = ++cnt; i += 2;
                }
            } else {
                if (t == 1) {
                    // 当原串当前字符是 2，而计数串当前字符为 1
                    // 往后构造形成的原串只能是 21，原串指针后移一位
                    sb.append("1");
                    f[i] = cnt; i++;
                } else {
                    // 当原串当前字符是 2，而计数串当前字符为 2
                    // 往后构造形成的原串只能是 221，原串指针后移两位
                    sb.append("21");
                    f[i] = f[i + 1] = cnt; i += 2;
                }
            }
        }
    }
    public int magicalString(int n) {
        return f[n];
    }

}
