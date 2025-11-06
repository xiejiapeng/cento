package linshen.structure.heap;

/*
给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表，用字母 A 到 Z 表示，以及一个冷却时间 n。
每个周期或时间间隔允许完成一项任务。任务可以按任何顺序完成，但有一个限制：两个 相同种类
的任务之间必须有长度为 n 的冷却时间。

返回完成所有任务所需要的 最短时间间隔
["A","A","A","B","B","B", "C","C","C", "D", "D", "E"]
2
ABC
ABC
ABC
DE
D

ABC
ABC
ABD
CED

ABCD
ABCD
ABCE

提示：

1 <= tasks.length <= 10^4
tasks[i] 是大写英文字母
0 <= n <= 100
 */

import java.util.*;

public class L621 {
    //todo hhhhh 典型的贪心构造题，太多的corner case需要考虑！！先把max的char都填满（此时记一次数），再往右添加（超过n的需要计数）！！
    public int leastInterval(char[] tasks, int n) {
        int[][] cnt = new int[26][2];
        for (int i = 0; i < 26; i++){
            cnt[i] = new int[]{i, 0};
        }
        for (int i = 0; i < tasks.length; i++){
            cnt[tasks[i]-'A'][1] = cnt[tasks[i]-'A'][1] + 1;
        }
        Arrays.sort(cnt, (o1, o2) -> -1 * Integer.compare(o1[1], o2[1]));
        int max = cnt[0][1];
        PriorityQueue<int[]> seats = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]);
            }
        });
        int total = 0;

        total += (max-1) * (n + 1);
        int t = 0;
        for (int[] x : cnt) {
            if(x[1] == max){
                total++;
                t++;
            }
        }
        total = (max-1) * Math.max(t, n+1) + t;
        for (int i = 0; i < max-1; i++) {
            seats.add(new int[]{i, t});
        }
//        System.out.println("total is " + total + ", t=" + t + ",max="+max);
        for (int i = 0; i < 26; i++){
            int c = cnt[i][1];
            Set<int[]> ns = new HashSet<>();
            if(c == max)continue;
            while (c-- > 0) {
                int[] next = seats.poll();
                ns.add(new int[]{next[0], next[1] + 1});
                if(next[1] > n)total++;
            }
            seats.addAll(ns);
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(new L621().leastInterval(new char[]{'A','A','A','A','B','C','D','G'}, 1));
    }
}
