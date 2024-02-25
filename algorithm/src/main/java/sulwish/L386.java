package sulwish;

import java.util.ArrayList;
import java.util.List;
/*
给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。

你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
 */

public class L386 {
    List<Integer> l = new ArrayList<>();

    public List<Integer> lexicalOrder(int n) {
        dfs(n, 0);
        return l;
    }

    private void dfs(int n, int sum) {
        if (sum > n) return;
        else {
            if (sum > 0) l.add(sum);
            for (int i = 0; i <= 9; i++) {
                if (i == 0 && sum == 0) continue;
                else {
                    dfs(n, sum * 10 + i);
                }
            }
        }
    }
}
