package index.digit;

/*
大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。

你可以搭配 任意 两道餐品做一顿大餐。

给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i 道餐品的美味程度，返回你可以用数组中的餐品做出的不同 大餐 的数量。结果需要对 109 + 7 取余。

注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class L1711 {
    int mod = (int) (1e9 + 7);

    public int countPairs(int[] deliciousness) {
        System.out.println("total " + deliciousness.length);
        Map<Integer, Integer> all = new HashMap<>();
        long ans = 0;
        for (int d : deliciousness) {
            all.put(d, all.getOrDefault(d, 0) + 1);
        }
        for (int x : deliciousness) {
            Set<Integer> res = remain(x);

            for (int y : res) {
                if (all.containsKey(y)) {
                    System.out.println("y=" + y + "cnt=" + all.get(y));
                    ans += (x == y ? all.get(y) - 1 : all.get(y));
//                    ans %= mod;
                }
            }
        }
        System.out.println(ans);
        return (int) ans / 2;
    }

    private Set<Integer> remain(int x) {
        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < 31; i++) {
            s.add((1 << i) - x);
        }
        return s;
    }
}
