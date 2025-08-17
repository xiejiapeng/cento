package linshen.structure.heap;

/*
汽车从起点出发驶向目的地，该目的地位于出发位置东面 target 英里处。

沿途有加油站，用数组 stations 表示。其中 stations[i] = [positioni, fueli] 表示第 i 个加油站位于出发位置东面 positioni 英里处，并且有 fueli 升汽油。

假设汽车油箱的容量是无限的，其中最初有 startFuel 升燃料。它每行驶 1 英里就会用掉 1 升汽油。当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。

为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 -1 。

注意：如果汽车到达加油站时剩余燃料为 0，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为 0，仍然认为它已经到达目的地。
 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class L871 {
    public static void main(String[] args) {
        System.out.println(new L871().minRefuelStops(100, 50, new int[][]{{25, 25}, {50, 50}}));
    }

    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int cur = 0;
        int all = startFuel;
        int cnt = 0;
        PriorityQueue<Integer> see = new PriorityQueue<>(Comparator.comparingInt(o -> -o));
        for (int i = 0; i < stations.length; i++) {
            if (cur + all >= target) return cnt;
            else {
                all -= (stations[i][0] - cur);
                while (all < 0) {
                    if (see.isEmpty()) return -1;
                    int t = see.poll();
                    all += t;
                    cnt += 1;
                }
                cur = stations[i][0];
                see.add(stations[i][1]);
            }
        }
        int diff = target - cur;
        all -= diff;
        while (all < 0) {
            if (see.isEmpty()) return -1;
            else {
                all += see.poll();
                cnt++;
            }
        }
        return cnt;
    }
}
