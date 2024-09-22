package index.digit;

/*
假设有从 1 到 n 的 n 个整数。用这些整数构造一个数组 perm（下标从 1 开始），只要满足下述条件 之一 ，该数组就是一个 优美的排列 ：

perm[i] 能够被 i 整除
i 能够被 perm[i] 整除
给你一个整数 n ，返回可以构造的 优美排列 的 数量 。
 */

import java.util.HashSet;
import java.util.Set;

public class L526 {
    int ans = 0;

    public int countArrangement(int n) {
        dfs(n, 1, new HashSet<>());
        return ans;
    }

    private void dfs(int n, int id, Set<Integer> used) {
        if (used.size() == n) {
            ans++;
        } else {
            for (int i = 1; i <= n; i++) {
                if (used.contains(i)) continue;
                else {
                    if (i % id == 0 || id % i == 0) {
                        used.add(i);
                        dfs(n, id + 1, used);
                        used.remove(i);
                    }
                }
            }
        }
    }
}
