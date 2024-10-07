package index.heap;

import java.util.PriorityQueue;

public class L871 {
    public static void main(String[] args) {
        System.out.println(new L871().minRefuelStops2(1000, 299, new int[][]{{13, 21}, {26, 115}, {100, 47}, {225, 99}, {299, 141}, {444, 198}, {608, 190}, {636, 157}, {647, 255}, {841, 123}}));
    }

    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        if (target <= startFuel) return 0;
        int loc = 0;
        int remain = startFuel;
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> -1 * Integer.compare(o1[1], o2[1]));
        int n = stations.length;
        int j = 0;
        int ans = 0;
        while (loc + remain < target) {
            if (j >= n) return -1;
            while (j < n && stations[j][0] - loc <= remain) {
                queue.add(new int[]{j, stations[j][0] + remain - (stations[j][0] - loc) + stations[j][1]});
                j++;
            }
            if (queue.isEmpty()) return -1;
            int[] x = queue.poll();
            loc = stations[x[0]][0];
            remain = x[1] - loc;
            ans++;
            queue.clear();
            j = x[0] + 1;
        }
        return ans;
    }

    //todo 不要clear queue，因为已经遍历过的加油站是可以添加的，只是相当于加了两次油
    public int minRefuelStops2(int target, int startFuel, int[][] stations) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        int n = stations.length, idx = 0;
        int remain = startFuel, loc = 0, ans = 0;
        while (loc < target) {
            if (remain == 0) {
                if (!q.isEmpty() && ++ans >= 0) remain += q.poll();
                else return -1;
            }
            loc += remain;
            remain = 0;
            while (idx < n && stations[idx][0] <= loc) {
                q.add(stations[idx++][1]);
            }
        }
        return ans;
    }
}
