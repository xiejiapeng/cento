package linshen.slide;

/*
给你一个整数 eventTime 表示一个活动的总时长，这个活动开始于 t = 0 ，结束于 t = eventTime 。

同时给你两个长度为 n 的整数数组 startTime 和 endTime 。它们表示这次活动中 n 个时间 没有重叠 的会议，其中第 i 个会议的时间为 [startTime[i], endTime[i]] 。

你可以重新安排 至多 k 个会议，安排的规则是将会议时间平移，且保持原来的 会议时长 ，你的目的是移动会议后 最大化 相邻两个会议之间的 最长 连续空余时间。

移动前后所有会议之间的 相对 顺序需要保持不变，而且会议时间也需要保持互不重叠。

请你返回重新安排会议以后，可以得到的 最大 空余时间。

注意，会议 不能 安排到整个活动的时间以外。
 */

public class L3439 {
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        int n = startTime.length;
        int len = 0;
        int max = 0;
        for (int right = 0; right < n; right++) {
            len += (endTime[right] - startTime[right]);
            int left = right - k + 1;
            if(left - 1 >= 0) {
                len -= (endTime[left-1] - startTime[left-1]);
            }
            if(left < 0)left = 0;
            int rightGap = right+1<n?startTime[right+1]:eventTime;
            int leftGap = left-1<0?0:endTime[left-1];
            int gap = rightGap - leftGap;
            max = Math.max(max, gap - len);
        }
        return max;
    }

    public static void main(String[] args) {
        /*
            0---[18,19,20],[20,21]
         */
        System.out.println(new L3439().maxFreeTime(21,2,new int[]{18,20}, new int[]{20,21}));
    }
}
