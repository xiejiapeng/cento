package linshen.backtrace;

/*
给你一个整数 n。
如果一个数满足以下条件，那么它被称为 特殊数 ：

它是一个 回文数 。
数字中每个数字 k 出现 恰好 k 次。
返回 严格 大于 n 的 最小 特殊数。
1 22 333 4444 55555 666666 7777777 88888888 999999999

只能有一个奇数

abcdefgh

如果一个整数正向读和反向读都相同，则它是 回文数 。例如，121 是回文数，而 123 不是。
提示:
0 <= n <= 10^15
 */

import java.util.*;
import java.util.stream.Collectors;

public class L3646 {
    // todo hhhhh 选项很少时，直接暴力枚举即可
    private String[] f = new String[]{"1", "22", "333", "4444", "55555", "666666", "7777777", "88888888", "999999999"};
    Set<Set<String>> candidates = new HashSet<>();
    private static final int ODD_MASK = 0x155;
    private static final int D = 9;

    private static final int[] size = new int[1 << D];

    static {
        // 预处理 size 数组，详细解释见视频讲解
        for (int mask = 1; mask < (1 << D); mask++) {
            int t = mask & ODD_MASK;
            if ((t & (t - 1)) > 0) { // 至少有两个奇数
                continue;
            }
            for (int i = 0; i < D; i++) {
                if ((mask >> i & 1) != 0) {
                    size[mask] += i + 1;
                }
            }
        }
    }

    public long specialPalindrome(long num) {
        int targetSize = String.valueOf(num).length();
        for (int mask = 1; mask < (1 << D); mask++) {
            int sz = size[mask];
            if (sz != targetSize && sz != targetSize + 1) {
                continue;
            }

            // 构造排列 perm
            int[] perm = new int[sz / 2];
            int idx = 0;
            int odd = 0;
            for (int x = 1; x <= D; x++) {
                if ((mask >> (x - 1) & 1) > 0) {
                    for (int k = 0; k < x / 2; k++) {
                        perm[idx++] = x;
                    }
                    if (x % 2 != 0) {
                        odd = x;
                    }
                }
            }

            boolean[] onPath = new boolean[perm.length];
            // 枚举 perm 的所有排列，生成对应的回文数
            dfs(0, 0, onPath, perm, odd, num);
        }
        return ans;
    }

    private long ans = Long.MAX_VALUE;

    // i 表示当前要填 perm 的第几个数，res 表示回文数的左半边
    private boolean dfs(int i, long res, boolean[] onPath, int[] perm, int odd, long num) {
        if (i == perm.length) {
            long v = res;
            if (odd > 0) {
                res = res * 10 + odd;
            }
            // 反转 x 的左半，拼在 x 后面
            while (v > 0) {
                res = res * 10 + v % 10;
                v /= 10;
            }
            if (res >= ans) { // 最优性剪枝：答案不可能变小
                return true;
            }
            if (res > num) { // 满足要求
                ans = res;
                return true;
            }
            return false;
        }

        // 见 47. 全排列 II
        for (int j = 0; j < perm.length; j++) {
            if (onPath[j] || (j > 0 && perm[j] == perm[j - 1] && !onPath[j - 1])) {
                continue;
            }
            onPath[j] = true;
            if (dfs(i + 1, res * 10 + perm[j], onPath, perm, odd, num)) {
                return true;
            }
            onPath[j] = false;
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(new L3646().specialPalindrome(2));
    }
}
