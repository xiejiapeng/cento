package sulwish;

import java.util.HashMap;
import java.util.Map;

/*
给定一个正整数 n ，你可以做如下操作：

如果 n 是偶数，则用 n / 2替换 n 。
如果 n 是奇数，则可以用 n + 1或n - 1替换 n 。
返回 n 变为 1 所需的 最小替换次数 。
 */
public class L397 {
    Map<Long, Integer> tmp = new HashMap<>();

    public int integerReplacement(int n) {
        return integerReplacement((long) n);
    }

    public int integerReplacement(long n) {
        if (tmp.containsKey(n)) return tmp.get(n);
        else {
            int ans;
            if (n == 1) ans = 0;
            else if (n == 2) ans = 1;
            else {
                if (n % 2 == 0) ans = 1 + integerReplacement(n / 2);
                else ans = 1 + Math.min(integerReplacement(n - 1), integerReplacement(n + 1));
            }
            tmp.put(n, ans);
            return ans;
        }
    }
}
