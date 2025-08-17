package linshen.structure.heap;

/*
给你一个整数数组 piles ，数组 下标从 0 开始 ，其中 piles[i] 表示第 i 堆石子中的石子数量。另给你一个整数 k ，请你执行下述操作 恰好 k 次：

选出任一石子堆 piles[i] ，并从中 移除 floor(piles[i] / 2) 颗石子。
注意：你可以对 同一堆 石子多次执行此操作。

返回执行 k 次操作后，剩下石子的 最小 总数。

floor(x) 为 小于 或 等于 x 的 最大 整数。（即，对 x 向下取整）。
 */

import java.util.PriorityQueue;

public class L1962 {
    public static void main(String[] args) {
        System.out.println(new L1962().minStoneSum(new int[]{5, 4, 9}, 2));
    }

    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        int sum = 0;
        for (int i = 0; i < piles.length; i++) {
            pq.add(piles[i]);
            sum += piles[i];
        }
        for (int i = 0; i <= k; i++) {
            int large = pq.poll();
            sum -= large / 2;
            pq.add(large - large / 2);
        }
        return sum;
    }
}
