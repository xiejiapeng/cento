package linshen.structure.heap;

/*
给你一个整数 n ，共有编号从 0 到 n - 1 的 n 个会议室。

给你一个二维整数数组 meetings ，其中 meetings[i] = [starti, endi] 表示一场会议将会在 半闭 时间区间 [starti, endi) 举办。
所有 starti 的值 互不相同 。

会议将会按以下方式分配给会议室：

每场会议都会在未占用且编号 最小 的会议室举办。
如果没有可用的会议室，会议将会延期，直到存在空闲的会议室。延期会议的持续时间和原会议持续时间 相同 。
当会议室处于未占用状态时，将会优先提供给原 开始 时间更早的会议。
返回举办最多次会议的房间 编号 。如果存在多个房间满足此条件，则返回编号 最小 的房间。

半闭区间 [a, b) 是 a 和 b 之间的区间，包括 a 但 不包括 b 。
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class L2402 {
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, Comparator.comparingInt(o -> o[0]));
        //todo hhhhh 与1882类似，注意这里busy需要多重排序
        PriorityQueue<long[]> busy = new PriorityQueue<>((a, b) -> {
            if (a[1] != b[1]) return Long.compare(a[1], b[1]);
            return Long.compare(a[0], b[0]); // 按房间号排
        });
        PriorityQueue<Long> idle = new PriorityQueue<>();
        for (int i = 0; i < n; i++){
            idle.add((long)i);
        }
        int[] cnt = new int[n];
        for (int[] meeting : meetings) {
            int start = meeting[0];
            int end = meeting[1];
            while(!busy.isEmpty() && busy.peek()[1] <= start) {
                long[] r = busy.poll();
                idle.add(r[0]);
            }
            if(idle.isEmpty()) {
                long[] r = busy.poll();
                cnt[(int)r[0]]++;
                busy.add(new long[]{r[0], r[1] -start + end});
//                System.out.println("room is " + r[0] + ", task is (" + start + "," + end + ")");
            } else {
                long t = idle.poll();
                cnt[(int)t]++;
                busy.add(new long[]{t, end});
//                System.out.println("room is " + t + ", task is (" + start + "," + end + ")");
            }

        }
        int mr = -1;
        int m = 0;
        for (int i = 0; i < n; i++){
            if(cnt[i] > mr) {
                mr = cnt[i];
                m = i;
            }
        }
        return m;
    }

    public static void main(String[] args) {
        System.out.println(new L2402().mostBooked(2, new int[][]{{0,10},{1,5},{2,7},{3,4}}));
//        System.out.println(new L2402().mostBooked(3, new int[][]{{1,20}, {2,10}, {3,5}, {4,9},{6,8}}));
    }
}
