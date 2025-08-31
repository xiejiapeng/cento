package linshen.greedy;

/*
给你一个整数 eventTime 表示一个活动的总时长，这个活动开始于 t = 0 ，结束于 t = eventTime 。

同时给你两个长度为 n 的整数数组 startTime 和 endTime 。它们表示这次活动中 n 个时间 没有重叠 的会议，
其中第 i 个会议的时间为 [startTime[i], endTime[i]] 。

你可以重新安排 至多 一个会议，安排的规则是将会议时间平移，且保持原来的 会议时长 ，你的目的是移动会议后 最大化
最长 连续空余时间。

请你返回重新安排会议以后，可以得到的 最大 空余时间。

注意，会议 不能 安排到整个活动的时间以外，且会议之间需要保持互不重叠。

注意：重新安排会议以后，会议之间的顺序可以发生改变。
提示：

1 <= eventTime <= 109
n == startTime.length == endTime.length
2 <= n <= 105
0 <= startTime[i] < endTime[i] <= eventTime
endTime[i] <= startTime[i + 1] 其中 i 在范围 [0, n - 2] 之间。
 */

import java.util.Arrays;
import java.util.Comparator;

public class L3440 {
    public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
        int n = startTime.length;
        int[][] all = new int[n][2];
        for (int i = 0; i < n; i++){
            all[i] = new int[]{startTime[i], endTime[i]};
        }
        Arrays.sort(all, Comparator.comparingInt(o -> o[0]));
        int[] left = new int[n];
        for (int i = 0; i < n; i++){
            if(i == 0)left[i] = all[i][0];
            else {
                left[i] = Math.max(left[i-1], all[i][0] - all[i-1][1]);
            }
        }
        int[] right = new int[n];
        for (int i = n - 1; i > -1; i--){
            if(i == n - 1)right[i] = eventTime - all[i][1];
            else {
                right[i] = Math.max(right[i+1], all[i+1][0] - all[i][1]);
            }
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            int len = all[i][1] - all[i][0];
            int gap = 0;
            int l = i-1>=0?all[i-1][1]:0;
            int r = i+1<n?all[i+1][0]:eventTime;
            gap += all[i][0] - l;
            gap += r - all[i][1];
            if((i - 1 >= 0 && left[i-1] >= len) || (i + 1 < n && right[i+1] >= len))gap += (all[i][1] - all[i][0]);
            max = Math.max(max, gap);
        }
        return max;
    }
}
