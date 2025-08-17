package linshen.structure.heap;

/*
给你一个正整数数组 nums 。每一次操作中，你可以从 nums 中选择 任意 一个数并将它减小到 恰好 一半。（注意，在后续操作中你可以对减半过的数继续执行操作）
请你返回将 nums 数组和 至少 减少一半的 最少 操作数。
 */

import java.util.PriorityQueue;

public class L2208 {
    public static void main(String[] args) {
        System.out.println(new L2208().halveArray(new int[]{1}));
    }

    public int halveArray(int[] nums) {
        PriorityQueue<Double> all = new PriorityQueue<>((a, b) -> Double.compare(b, a));
        double sum = 0;
        for (int x : nums) {
            all.add((double) x);
            sum += x;
        }
        int c = 0;
        double p = sum;
        while (sum > p / 2) {
            double t = all.poll();
            sum -= t / 2;
            all.add(t / 2);
            c++;
        }
        return c;
    }
}
