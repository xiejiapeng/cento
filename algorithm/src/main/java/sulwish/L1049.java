package sulwish;

/*
有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。

每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：

如果 x == y，那么两块石头都会被完全粉碎；
如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
a<b<c
 */

import java.util.Arrays;

public class L1049 {
    public int lastStoneWeightII(int[] stones) {
        int sum = Arrays.stream(stones).sum();
        int target = sum / 2;

        int n = stones.length;
        int[][] f = new int[n][target + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= target; j++) {
                if (i == 0) {
                    if (stones[i] <= j) f[i][j] = stones[i];
                    continue;
                } else if (j == 0) {
                    f[i][j] = 0;
                    continue;
                } else {
                    f[i][j] = f[i - 1][j];
                    if (stones[i] <= j) {
                        f[i][j] = Math.max(f[i][j], stones[i] + f[i - 1][j - stones[i]]);
                    }
                }

            }
        }
        return sum - 2 * f[n - 1][target];
    }
}
