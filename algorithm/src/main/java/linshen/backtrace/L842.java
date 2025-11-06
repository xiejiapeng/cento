package linshen.backtrace;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/*
给定一个数字字符串 num，比如 "123456579"，我们可以将它分成「斐波那契式」的序列 [123, 456, 579]。

形式上，斐波那契式 序列是一个非负整数列表 f，且满足：

0 <= f[i] < 231 ，（也就是说，每个整数都符合 32 位 有符号整数类型）
f.length >= 3
对于所有的0 <= i < f.length - 2，都有 f[i] + f[i + 1] = f[i + 2]
另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。

返回从 num 拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 []。

提示：

1 <= num.length <= 200
num 中只含有数字
 */

public class L842 {
    int INF = Integer.MAX_VALUE;
    public List<Integer> splitIntoFibonacci(String num) {
        long l = 0;
        for (int i = 0; i < num.length() - 2; i++) {
            l = l * 10 + (num.charAt(i) - '0');

            if(l > INF)break;
            long r = 0;
            for (int j = i + 1; j < num.length() - 1; j++){
                r = r * 10 + (num.charAt(j) - '0');
                if(r > INF)break;
                if((l != 0 && num.charAt(0) == '0') || (r != 0 && num.charAt(i+1) == '0'))break;
                List<Long> ans = new ArrayList<>();
                long ll = l;
                long rr = r;
                ans.add(ll);
                ans.add(rr);

                String target = String.valueOf(ll + rr);
                int start = j + 1;
                while (start < num.length()) {
                    if(Long.valueOf(target) > INF)break;
                    if(Long.valueOf(target) != 0 && num.charAt(start) == '0')break;
                    if(num.substring(start).startsWith(target)) {
                        start += target.length();
                        ll = rr;
                        rr = Long.parseLong(target);
                        ans.add(rr);
                        target = String.valueOf(ll + rr);
                    } else {
                        break;
                    }
                }
                if(start == num.length()) {
                    return ans.stream().mapToInt(Long::intValue).boxed().collect(Collectors.toList());
                }

            }
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        System.out.println(new L842().splitIntoFibonacci("214748364721474836422147483641"));
    }
}
