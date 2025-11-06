package linshen.structure;

/*
给你一个下标从 0 开始的二维整数数组 flowers ，其中 flowers[i] = [starti, endi] 表示第 i 朵花的
花期 从 starti 到 endi （都 包含）。同时给你一个下标从 0 开始大小为 n 的整数数组 people ，people[i] 是第 i 个人来看花的时间。

请你返回一个大小为 n 的整数数组 answer ，其中 answer[i]是第 i 个人到达时在花期内花的 数目 。

提示：

1 <= flowers.length <= 5 * 104
flowers[i].length == 2
1 <= starti <= endi <= 109
1 <= people.length <= 5 * 104
1 <= people[i] <= 109
 */

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class L2251 {
    public int[] fullBloomFlowers(int[][] flowers, int[] people) {
        //todo h 用treemap+二分来节约空间，但时间复杂度会有一定上升
        TreeMap<Integer, Integer> diff = new TreeMap<>();
        for (int i = 0; i < flowers.length; i++) {
            int start = flowers[i][0];
            int end = flowers[i][1];
            diff.put(start, diff.getOrDefault(start, 0) + 1);
            diff.put(end + 1, diff.getOrDefault(end + 1, 0) - 1);
        }
        List<Integer> days = new ArrayList<>();
        for (int k : diff.keySet()) {
            days.add(k);
        }
        int n = days.size();
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + diff.get(days.get(i - 1));
        }
        int m = people.length;
        int[] ans = new int[m];
        for (int i = 0; i < people.length; i++) {
            int p = people[i];
            int t = find(days, 0, days.size() - 1, p);
            ans[i] = sum[t + 1];
        }
        return ans;
    }

    private int find(List<Integer> days, int start, int end, int k) {
        if (start == end) {
            if (days.get(start) <= k) return start;
            else return -1;
        } else if (start == end - 1) {
            if (days.get(end) <= k) return end;
            else if (days.get(start) <= k) return start;
            else return -1;
        } else {
            int mid = (start + end) / 2;
            if (days.get(mid) <= k) return find(days, mid, end, k);
            else return find(days, start, mid - 1, k);
        }
    }
}
