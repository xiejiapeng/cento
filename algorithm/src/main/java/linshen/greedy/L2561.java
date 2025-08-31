package linshen.greedy;

/*
你有两个果篮，每个果篮中有 n 个水果。给你两个下标从 0 开始的整数数组 basket1 和 basket2 ，
用以表示两个果篮中每个水果的交换成本。你想要让两个果篮相等。为此，可以根据需要多次执行下述操作：

选中两个下标 i 和 j ，并交换 basket1 中的第 i 个水果和 basket2 中的第 j 个水果。
交换的成本是 min(basket1i,basket2j) 。
根据果篮中水果的成本进行排序，如果排序后结果完全相同，则认为两个果篮相等。

返回使两个果篮相等的最小交换成本，如果无法使两个果篮相等，则返回 -1 。
 */

import java.util.*;

public class L2561 {
    public static void main(String[] args) {
        System.out.println(new L2561().minCost(new int[]{84, 80, 43, 8, 80, 88, 43, 14, 100, 88}, new int[]{32, 32, 42, 68, 68, 100, 42, 84, 14, 8}));
    }

    /*
    2 2 2 4
    1 1 2 4
     */
    public long minCost(int[] basket1, int[] basket2) {
        Arrays.sort(basket1);
        Arrays.sort(basket2);

        Map<Integer, Integer> c1 = new HashMap<>();
        Map<Integer, Integer> c2 = new HashMap<>();
        Set<Integer> all = new HashSet<>();
        for (int x : basket1) {
            c1.put(x, c1.getOrDefault(x, 0) + 1);
            all.add(x);
        }
        for (int x : basket2) {
            c2.put(x, c2.getOrDefault(x, 0) + 1);
            all.add(x);
        }

        // 记录全局最小值
        //中转最小值！！！todo hhhhh 涉及到交换的代价，需要引入并考虑全局最小值作为中转
        int globalMin = Math.min(basket1[0], basket2[0]);

        // 统计多余的部分
        List<Integer> extra1 = new ArrayList<>();
        List<Integer> extra2 = new ArrayList<>();
        for (int x : all) {
            int d = c1.getOrDefault(x, 0) - c2.getOrDefault(x, 0);
            if (d % 2 != 0) return -1; // 不可能配平
            if (d > 0) {
                //todo hhh 这里展开就好，不要用map了，太复杂了
                for (int i = 0; i < d / 2; i++) extra1.add(x);
            } else if (d < 0) {
                for (int i = 0; i < -d / 2; i++) extra2.add(x);
            }
        }

        if (extra1.isEmpty()) return 0; // 已经相等

        Collections.sort(extra1); // 升序
        Collections.sort(extra2, Collections.reverseOrder()); // 降序

        long ans = 0;
        for (int i = 0; i < extra1.size(); i++) {
            int x = extra1.get(i);
            int y = extra2.get(i);
            // 代价 = min(min(x, y), 2*globalMin)
            ans += Math.min(Math.min(x, y), 2 * globalMin);
        }
        return ans;
    }
}
