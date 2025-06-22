package linshen.dp;

/*
给你两个 正 整数 startPos 和 endPos 。最初，你站在 无限 数轴上位置 startPos 处。在一步移动中，
你可以向左或者向右移动一个位置。

给你一个正整数 k ，返回从 startPos 出发、恰好 移动 k 步并到达 endPos 的 不同 方法数目。
由于答案可能会很大，返回对 109 + 7 取余 的结果。

如果所执行移动的顺序不完全相同，则认为两种方法不同。
[s-k,s+k]
[0,2k]
e -> (end-start)+k

注意：数轴包含负整数。
提示：
1 <= startPos, endPos, k <= 1000
 */

import java.util.HashMap;
import java.util.Map;

public class L2400 {
    int mod = (int)(1e9+7);
    Map<Integer, Map<Integer, Long>> ans = new HashMap<>();
    public int numberOfWays(int startPos, int endPos, int k) {
        return (int)(get(startPos, endPos, k) % mod);
    }

    private long get(int s, int e, int t) {
        if(ans.containsKey(s) && ans.get(s).containsKey(t)) {
            return ans.get(s).get(t);
        } else {
            ans.putIfAbsent(s, new HashMap<>());
            Map<Integer, Long> tmp = ans.get(s);
            if(t == 1) {
                if(s == e - 1 || s == e + 1) {
                    tmp.put(t, 1L);
                } else {
                    tmp.put(t, 0L);
                }
            } else {
                //move to left
                int left = s - 1;
                long l = get(s-1, e, t-1);
                //move to right
                int right = s + 1;
                long r = get(s+1, e, t-1);
                long a = (l + r) % mod;
                tmp.put(t, a);
            }
            ans.put(s, tmp);
            return ans.get(s).get(t);
        }
    }

    public static void main(String[] args) {
        System.out.println(new L2400().numberOfWays(1,2,3));
    }
}
