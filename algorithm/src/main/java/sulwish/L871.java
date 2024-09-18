package sulwish;

/*
汽车从起点出发驶向目的地，该目的地位于出发位置东面 target 英里处。

沿途有加油站，用数组 stations 表示。其中 stations[i] = [positioni, fueli] 表示第 i 个加油站位于出发位置
东面 positioni 英里处，并且有 fueli 升汽油。

假设汽车油箱的容量是无限的，其中最初有 startFuel 升燃料。它每行驶 1 英里就会用掉 1 升汽油。
当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。

为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 -1 。

注意：如果汽车到达加油站时剩余燃料为 0，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为 0，
仍然认为它已经到达目的地。
 */

import java.util.PriorityQueue;

public class L871 {
    public int minRefuelStops2(int target, int startFuel, int[][] stations) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b)->b-a);
        int n = stations.length, idx = 0;
        int remain = startFuel, loc = 0, ans = 0;
        while (loc < target) {
            if (remain == 0) {
                if (!q.isEmpty() && ++ans >= 0) {
                    int x = q.poll();
                    System.out.println(x);
                    remain += x;
                }
                else return -1;
            }
            loc += remain; remain = 0;
            while (idx < n && stations[idx][0] <= loc) q.add(stations[idx++][1]);
        }
        return ans;
    }
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int limit = startFuel;
        int cur = 0;
        int i = 0;
        int cap = startFuel;
        int step = 0;
        while (limit < target) {
            int maxLimit = -1;
            int maxPos = -1;
            int maxCap = -1;
            for (; i < stations.length && (stations[i][0]-cur <= cap); i++){
                if(stations[i][0] + cap - (stations[i][0]-cur) + stations[i][1] > maxLimit) {
                    maxLimit = stations[i][0] + cap - (stations[i][0]-cur) + stations[i][1];
                    maxPos = stations[i][0];
                    maxCap = cap - (stations[i][0]-cur) + stations[i][1];
                }
            }

            if(maxLimit == limit)return -1;
            limit = maxLimit;
            cur = maxPos;
            cap = maxCap;
            step++;
            System.out.println("cur="+cur+",limit="+limit+",cap="+cap);
        }
        return step;
    }

    public static void main(String[] args) {
        int[][] stations = {{10,60},{20,30},{30,30},{60,40}};
        System.out.println(new L871().minRefuelStops2(100, 10, stations));
    }
}
