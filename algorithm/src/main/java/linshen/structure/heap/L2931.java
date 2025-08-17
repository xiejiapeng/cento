package linshen.structure.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
给你一个下标从 0 开始大小为 m * n 的整数矩阵 values ，表示 m 个不同商店里 m * n 件不同的物品。每个商店有 n 件物品，
第 i 个商店的第 j 件物品的价值为 values[i][j] 。除此以外，第 i 个商店的物品已经按照价值非递增排好序了，
也就是说对于所有 0 <= j < n - 1 都有 values[i][j] >= values[i][j + 1] 。

每一天，你可以在一个商店里购买一件物品。具体来说，在第 d 天，你可以：

选择商店 i 。
购买数组中最右边的物品 j ，开销为 values[i][j] * d 。换句话说，选择该商店中还没购买过的物品中最大的下标 j ，并且花费 values[i][j] * d 去购买。
注意，所有物品都视为不同的物品。比方说如果你已经从商店 1 购买了物品 0 ，你还可以在别的商店里购买其他商店的物品 0 。

请你返回购买所有 m * n 件物品需要的 最大开销 。
 */
public class L2931 {
    public long maxSpending(int[][] values) {
        PriorityQueue<int[]> all = new PriorityQueue<>(Comparator.comparingInt(a -> values[a[0]][a[1]]));
        int m = values.length;
        int n = values[0].length;
        for (int i = 0; i < m; i++) {
            all.add(new int[]{i, n - 1});
        }

        long d = 1;
        long sum = 0;
        while (!all.isEmpty()) {
            int[] x = all.poll();
            sum += d * values[x[0]][x[1]];
            if (x[1] >= 1) all.add(new int[]{x[0], x[1] - 1});
        }
        return sum;
    }
}
