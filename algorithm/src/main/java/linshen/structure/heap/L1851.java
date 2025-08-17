package linshen.structure.heap;

/*
给你一个二维整数数组 intervals ，其中 intervals[i] = [lefti, righti] 表示第 i 个区间开始
于 lefti 、结束于 righti（包含两侧取值，闭区间）。区间的 长度 定义为区间中包含的整数数目，
更正式地表达是 righti - lefti + 1 。

再给你一个整数数组 queries 。第 j 个查询的答案是满足 lefti <= queries[j] <= righti 的 长度最小
区间 i 的长度 。如果不存在这样的区间，那么答案是 -1 。

以数组形式返回对应查询的所有答案。
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class L1851 {
    //todo hhh 这种多个队列的思路，很适合多重排序的题，在这里需要对start，end，len同时排序，但是对于start和end其实只需要满足条件；这时一般要在几个队列中挪来挪去
    public int[] minInterval(int[][] intervals, int[] queries) {
        int n = queries.length;
        int[][] qs = new int[n][2];
        for (int i = 0; i < queries.length; i++){
            qs[i] = new int[]{i, queries[i]};
        }
        int[] ans = new int[n];
        Arrays.sort(qs, Comparator.comparingInt(o -> o[1]));
        PriorityQueue<Integer> eq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(intervals[o1][1], intervals[o2][1]);
            }
        });
        TreeSet<Integer> len = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(intervals[o1][1] - intervals[o1][0] != intervals[o2][1] - intervals[o2][0]) {
                    return Integer.compare(intervals[o1][1] - intervals[o1][0], intervals[o2][1] - intervals[o2][0]);
                } else {
                    return Integer.compare(o1,o2);
                }
            }
        });
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int j = 0;
        for (int i = 0; i < qs.length; i++){
            int id = qs[i][0];
            int qu = qs[i][1];
            while (j < intervals.length && intervals[j][0] <= qu) {
                eq.add(j);
                len.add(j);
                j++;
            }
            while (!eq.isEmpty() && intervals[eq.peek()][1] < qu){
                int x = eq.poll();
                len.remove(x);
            }
            if(len.isEmpty()){
                ans[id] = -1;
            } else {
                int t = len.first();
                ans[id] = intervals[t][1] - intervals[t][0] + 1;
            }

        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new L1851().minInterval(new int[][]{{2,3},{2,5},{1,8},{20,25}}, new int[]{19})));
    }
}
