package sulwish;

/*
给你一个 下标从 0 开始 的正整数数组 w ，其中 w[i] 代表第 i 个下标的权重。

请你实现一个函数 pickIndex ，它可以 随机地 从范围 [0, w.length - 1] 内（含 0 和 w.length - 1）
选出并返回一个下标。选取下标 i 的 概率 为 w[i] / sum(w) 。

例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3) = 0.25 （即，25%），而选取下标 1 的概率为 3 / (1 + 3) = 0.75（即，75%）。

 */

import java.util.Random;
import java.util.TreeMap;

public class L528 {
    class Solution {
        TreeMap<Integer,Integer> all = new TreeMap<>();
        Random r = new Random();
        int sum = 0;

        public Solution(int[] w) {
            int s = 0;
            for (int i = 0; i < w.length; i++){
                s += w[i];
                System.out.println("s="+s+",i="+i);
                all.put(s, i);
            }
            sum = s;
        }

        public int pickIndex() {
            int s = r.nextInt(sum);
            System.out.println(s);
            return all.ceilingEntry(s).getValue();
        }
    }

    public static void main(String[] args) {
        L528.Solution s = new L528().new Solution(new int[]{3,14,1,7});
        for (int i = 0; i < 10; i++){
            System.out.println(s.pickIndex());
        }

    }

}
