package linshen.backtrace;

/*
给你两个整数 n 和 k，将数字 n 恰好分割成 k 个正整数，使得这些整数的 乘积 等于 n。

返回一个分割方案，使得这些数字中 最大值 和 最小值 之间的 差值 最小化。结果可以以 任意顺序 返回。
提示：

4 <= n <= 10^5
2 <= k <= 5
k 严格小于 n 的正因数的总数。
 */

import java.util.*;

import java.util.*;

public class L3669 {
    //todo hhhhh 原来的做法是将n进行质数分解，然后在质因子上dfs，这样情况会比较多，分支爆炸；官方解法是遍历n的因子(不是质因子）,并放入桶中
    public int[] minDifference(int n, int k) {
        int[] path = new int[k];
        dfs(0, n, Integer.MAX_VALUE, 0, path);
        return ans;
    }

    private int minDiff = Integer.MAX_VALUE;
    private int[] ans;

    private void dfs(int i, int n, int mn, int mx, int[] path) {
        if (i == path.length - 1) {
            int d = Math.max(mx, n) - Math.min(mn, n); // 最后一个数是 n
            if (d < minDiff) {
                minDiff = d;
                path[i] = n;
                ans = path.clone();
            }
            return;
        }
        for (int d = 1; d <= n; d++) { // 枚举 n 的因子 d
            if (n % d == 0) {
                path[i] = d; // 直接覆盖，无需恢复现场
                dfs(i + 1, n / d, Math.min(mn, d), Math.max(mx, d), path);
            }
        }
    }
}

