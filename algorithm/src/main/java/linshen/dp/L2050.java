package linshen.dp;

/*
给你一个整数 n ，表示有 n 节课，课程编号从 1 到 n 。同时给你一个二维整数数组 relations ，
其中 relations[j] = [prevCoursej, nextCoursej] ，表示课程 prevCoursej 必须在
课程 nextCoursej 之前 完成（先修课的关系）。同时给你一个下标从 0 开始的整数数组 time ，
其中 time[i] 表示完成第 (i+1) 门课程需要花费的 月份 数。

请你根据以下规则算出完成所有课程所需要的 最少 月份数：

如果一门课的所有先修课都已经完成，你可以在 任意 时间开始这门课程。
你可以 同时 上 任意门课程 。
请你返回完成所有课程所需要的 最少 月份数。

注意：测试数据保证一定可以完成所有课程（也就是先修课的关系构成一个有向无环图）。
 */

import java.util.*;

public class L2050 {
    public int minimumTime(int n, int[][] relations, int[] time) {
        int ans = 0;
        Map<Integer, Set<Integer>> prev = new HashMap<>();
        Map<Integer, Set<Integer>> next = new HashMap<>();
        for (int[] r : relations) {
            int x = r[0];
            int y = r[1];
            prev.putIfAbsent(y, new HashSet<>());
            prev.get(y).add(x);
            next.putIfAbsent(x, new HashSet<>());
            next.get(x).add(y);
        }

        List<int[]> study = new ArrayList<>();
        for (int i = 0; i < n; i++){
            if(!prev.containsKey(i+1)){
                study.add(new int[]{i+1, time[i]});
                ans = Math.max(ans, time[i]);
            }
        }

        while (!study.isEmpty()){
            Map<Integer, Integer> input = new HashMap<>();
            //todo hhhhh 注意，这个一个错误的版本，应该把ts放在while的外面。对于y的最大时间的统计，不应该局限在某一次循环内，如果某个很大的前置x，不能将y的input变成0，x的time对y的影响将会丢失！！！
            Map<Integer, Integer> ts = new HashMap<>();
            for (int[] p : study) {
                int x = p[0];
                int t = p[1];
                if(next.containsKey(x)) {
                    for (int y : next.get(x)) {
                        prev.get(y).remove(x);
                        input.put(y, prev.get(y).size());
                        ts.put(y, Math.max(ts.getOrDefault(y, -1), t + time[y-1]));
                    }
                }

            }
            List<int[]> ns = new ArrayList<>();
            for (int y : input.keySet()) {
                if(input.get(y) == 0) {
                    ans = Math.max(ans, ts.get(y));
                    ns.add(new int[]{y, ts.get(y)});
                }
            }
            study = ns;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new L2050().minimumTime(3, new int[][]{{1,3},{2,3}}, new int[]{3,2,5}));
    }
}
