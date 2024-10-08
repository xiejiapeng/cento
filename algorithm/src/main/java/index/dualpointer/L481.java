package index.dualpointer;

/*
神奇字符串 s 仅由 '1' 和 '2' 组成，并需要遵守下面的规则：

神奇字符串 s 的神奇之处在于，串联字符串中 '1' 和 '2' 的连续出现次数可以生成该字符串。
s 的前几个元素是 s = "1221121221221121122……" 。如果将 s 中连续的若干 1 和 2 进行分组，可以得到 "1 22 11 2 1 22 1 22 11 2 11 22 ......" 。每组中 1 或者 2 的出现次数分别是 "1 2 2 1 1 2 1 2 2 1 2 2 ......" 。上面的出现次数正是 s 自身。

给你一个整数 n ，返回在神奇字符串 s 的前 n 个数字中 1 的数目。
12211

 */

public class L481 {
    public int magicalString(int n) {
        String s = "1";
        int c = 2;
        int last = 1;
        int next = 1;
        while (s.length() < n) {
            char ch = last == 1 ? '2' : '1';
            for (int i = 0; i < c; i++){
                s += ch;
            }
            next++;
            c = s.charAt(next)-'0';
            last = last == 1 ? 2 : 1;
        }
        int ans = 0;
        System.out.println(s);
        for (int i = 0; i < n; i++){
            if(s.charAt(i)=='1')ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new L481().magicalString(2));
    }
}
