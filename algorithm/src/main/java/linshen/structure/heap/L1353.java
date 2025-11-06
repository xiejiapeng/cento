package linshen.structure.heap;

/*
给你一个数组 events，其中 events[i] = [startDayi, endDayi] ，表示会议 i 开始于 startDayi ，
结束于 endDayi 。

你可以在满足 startDayi <= d <= endDayi 中的任意一天 d 参加会议 i 。在任意一天 d 中只能参加一场会议。

请你返回你可以参加的 最大 会议数目。
提示：

1 <= events.length <= 105
events[i].length == 2
1 <= startDayi <= endDayi <= 105
 */

import java.util.*;

public class L1353 {
    public int maxEvents(int[][] events) {
        Map<Integer, Set<Integer>> start = new HashMap<>();
        Map<Integer, Set<Integer>> end = new HashMap<>();
        for (int i = 0; i < events.length; i++){
            int[] e = events[i];
            start.putIfAbsent(e[0], new HashSet<>());
            start.get(e[0]).add(i);
            end.putIfAbsent(e[1]+1, new HashSet<>());
            end.get(e[1]+1).add(i);
        }
        int ans = 0;
        PriorityQueue<Integer> x = new PriorityQueue<>(Comparator.comparingInt(o -> events[o][1]));
        for (int i = 1; i <= 100005; i++){
            for (int s : start.getOrDefault(i, new HashSet<>())) {
                x.add(s);
            }
            for (int s : end.getOrDefault(i, new HashSet<>())) {
                x.remove(s);
            }
            if(!x.isEmpty()) {
                x.poll();
                ans++;
            }
        }
        return ans;
    }

    //todo hhhhh 参考答案的优化点，不需要遍历每一个day，只要跳转到会有会议过期的day，当然要添加中间新增的会议；答案确实简洁高效
    public int maxEvents2(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]); // 按开始时间排
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 最小堆，存结束时间

        int i = 0, n = events.length, day = 0, ans = 0;
        while (i < n || !pq.isEmpty()) {
            if (pq.isEmpty()) {
                day = events[i][0];
            }

            // 把今天开始的会议加入堆
            while (i < n && events[i][0] <= day) {
                pq.add(events[i][1]);
                i++;
            }

            // 清理已经过期的会议
            while (!pq.isEmpty() && pq.peek() < day) {
                pq.poll();
            }

            // 参加一个结束最早的会议
            if (!pq.isEmpty()) {
                pq.poll();
                ans++;
            }

            day++;
        }
        return ans;
    }

}
