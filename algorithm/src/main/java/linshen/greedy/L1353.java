package linshen.greedy;

/*
给你一个数组 events，其中 events[i] = [startDayi, endDayi] ，表示会议 i 开始于 startDayi ，结束于 endDayi 。

你可以在满足 startDayi <= d <= endDayi 中的任意一天 d 参加会议 i 。在任意一天 d 中只能参加一场会议。

请你返回你可以参加的 最大 会议数目。
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class L1353 {
    //todo hhhhh 易错题，蕴含idle/busy的双排序思想
    public int maxEvents(int[][] events) {
        PriorityQueue<int[]> idle = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        PriorityQueue<int[]> busy = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        int last = -1;
        int flag = -1;
        int cnt = 0;
        for (int[] event : events) {
            busy.add(event);
        }
        while (!busy.isEmpty() || !idle.isEmpty()) {
            if(!busy.isEmpty()) {
                if(busy.peek()[0] > last) {
                    if(idle.isEmpty()){
                        last = busy.peek()[0];
                        continue;
                    }
                } else {
                    while (!busy.isEmpty() && busy.peek()[0] <= last) {
                        idle.add(busy.poll());
                    }
                }
            }
            if(!idle.isEmpty()) {
                int[] t = idle.poll();
                int can = Math.max(flag+1, t[0]);
                if(can <= t[1]) {
                    last = can + 1;
                    flag = can;
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(new L1353().maxEvents(new int[][]{{7,11},{7,11},{7,11},{9,10},{9,11}}));
    }
}
