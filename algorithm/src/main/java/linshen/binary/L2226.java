package linshen.binary;

import java.util.Arrays;

/*
给你一个 下标从 0 开始 的整数数组 candies 。数组中的每个元素表示大小为 candies[i] 的一堆糖果。
你可以将每堆糖果分成任意数量的 子堆 ，但 无法 再将两堆合并到一起。

另给你一个整数 k 。你需要将这些糖果分配给 k 个小孩，使每个小孩分到 相同 数量的糖果。每个小孩可以拿走 至多一堆 糖果，有些糖果可能会不被分配。

返回每个小孩可以拿走的 最大糖果数目 。
 */
public class L2226 {
    public int maximumCandies(int[] candies, long k) {
        int n = candies.length;
        long total = Arrays.stream(candies).asLongStream().sum();
        long m = total / k;
        return (int)findMax(candies, 0, m, k);
    }

    private long findMax(int[] candies, long left, long right, long k) {
        if(left == right) return left;
        else if(left == right - 1) {
            if(check(candies, k, right))return right;
            else return left;
        } else {
            long mid = (left + right) / 2;
            if(check(candies, k, mid)) return findMax(candies, mid, right, k);
            else return findMax(candies, left, mid - 1, k);
        }
    }

    private boolean check(int[] candies, long k, long t) {
        long cnt = 0;
        for (int x : candies) {
            cnt += x / t;
        }
        return cnt >= k;
    }
}
