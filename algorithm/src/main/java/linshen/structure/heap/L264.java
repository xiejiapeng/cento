package linshen.structure.heap;

/*
给你一个整数 n ，请你找出并返回第 n 个 丑数 。

丑数 就是质因子只包含 2、3 和 5 的正整数。
 */

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class L264 {
    public int nthUglyNumber(int n) {
        LinkedList<Integer> all = new LinkedList<>();
        all.add(1);
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(o -> o[0] * all.get(o[1] + 1)));
        q.add(new int[]{2, 0});
        q.add(new int[]{3, 0});
        q.add(new int[]{5, 0});
        while (all.size() < n) {
            int[] t = q.poll();
            int next = t[0] * all.get(t[1] + 1);
            if (!all.contains(next)) {
                all.addLast(next);
            }
            q.add(new int[]{t[0], t[1] + 1});
        }
        return all.getLast();
    }
}
