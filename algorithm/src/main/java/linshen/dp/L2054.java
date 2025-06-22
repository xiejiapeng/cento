package linshen.dp;

/*
给你一个下标从 0 开始的二维整数数组 events ，其中 events[i] = [startTimei, endTimei, valuei] 。
第 i 个活动开始于 startTimei ，结束于 endTimei ，如果你参加这个活动，那么你可以得到价值 valuei 。你 最多 可以参加 两个时间不重叠 活动，使得它们的价值之和 最大 。

请你返回价值之和的 最大值 。

注意，活动的开始时间和结束时间是 包括 在活动时间内的，也就是说，
你不能参加两个活动且它们之一的开始时间等于另一个活动的结束时间。更具体的，如果你参加一个活动，
且结束时间为 t ，那么下一个活动必须在 t + 1 或之后的时间开始。
 */

import java.util.Arrays;
import java.util.Comparator;

public class L2054 {
    public int maxTwoEvents(int[][] events) {
        int n = events.length;
        Arrays.sort(events, Comparator.comparingInt(o -> o[0]));
        int[] dp = new int[n];
        int[] m = new int[n];
        m[n-1] = events[n-1][1];
        int max = m[n-1];
        for (int i = n - 2; i > -1; i--) {
            int t = first(events[i][1], events, i + 1, n - 1);
            if(t != -1) {
                dp[i] = events[i][2] + m[t];
            } else {
                dp[i] = events[i][2];
            }
            m[i] = Math.max(m[i+1], events[i][2]);
            max = Math.max(max, dp[i]);
            if(max == 7) {
                System.out.println("i="+i+",t="+t);
            }
        }
        return max;
    }

    private int first(int x, int[][] events, int start, int end) {
        if(start == end) {
            if(events[start][0] > x) return start;
            else return -1;
        } else if(start == end - 1) {
            if(events[start][0] > x) return start;
            else if(events[end][0] > x) return end;
            else return -1;
        } else {
            int mid = (start + end) / 2;
            if(events[mid][0] > x)return first(x, events, start, mid);
            else return first(x, events, mid + 1, end);
        }
    }
}
