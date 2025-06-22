package linshen.dp;

/*
汽车从起点出发驶向目的地，该目的地位于出发位置东面 target 英里处。

沿途有加油站，用数组 stations 表示。其中 stations[i] = [positioni, fueli] 表示
第 i 个加油站位于出发位置东面 positioni 英里处，并且有 fueli 升汽油。

假设汽车油箱的容量是无限的，其中最初有 startFuel 升燃料。它每行驶 1 英里就会用掉 1 升汽油。
当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。

为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 -1 。

注意：如果汽车到达加油站时剩余燃料为 0，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为 0，
仍然认为它已经到达目的地。
提示：

1 <= target, startFuel <= 109
0 <= stations.length <= 500
1 <= positioni < positioni+1 < target
1 <= fueli < 109
 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class L871 {
    //todo h remember it
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        PriorityQueue<Integer> see = new PriorityQueue<>((o1, o2) -> -1 * Integer.compare(o1, o2));
        int n = stations.length;
        int remain = startFuel;
        int cnt = 0;
        int loc = 0;
        int next = 0;
        while (true) {
            if(next == n){
                if (target - loc > remain) {
                    if(see.isEmpty())return -1;
                    int t = see.poll();
                    remain += t;
                    cnt++;
                }
                if(target-loc <= remain)return cnt;
            } else {
                //go to next
                if(stations[next][0] - loc <= remain) {
                    remain -= (stations[next][0] - loc);
                    loc = stations[next][0];
                    see.add(stations[next][1]);
                    next++;
                } else {
                    if(see.isEmpty()) {
                        return -1;
                    } else {
                        int t = see.poll();
                        remain += t;
                        cnt++;
                    }
                }
            }

        }
    }

    public static void main(String[] args) {
        System.out.println(new L871().minRefuelStops(100, 50, new int[][]{{25,25},{50,50}}));
    }
}
