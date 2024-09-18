package sulwish;

/*
给你一个整数数组 jobs ，其中 jobs[i] 是完成第 i 项工作要花费的时间。

请你将这些工作分配给 k 位工人。所有工作都应该分配给工人，且每项工作只能分配给一位工人。工人的 工作时间 是完成分配给他们的所有工作花费时间的总和。
请你设计一套最佳的工作分配方案，使工人的 最大工作时间 得以 最小化 。

返回分配方案中尽可能 最小 的 最大工作时间 。
 */

import java.util.*;

public class L1723 {
    int min = Integer.MAX_VALUE;
    int i = 0;

    public static void main(String[] args) {
        System.out.println(new L1723().minimumTimeRequired(new int[]{7063, 6355, 277, 600, 1126, 762, 5813, 847, 468, 2266, 9995, 8509}, 10));
    }

    public int minimumTimeRequired(int[] jobs, int k) {
        dfs(jobs, 0, k, new int[k]);
        return min;
    }

    private void dfs(int[] jobs, int cur, int k, int[] assign) {
        System.out.println("fuck " + i++);
        if (Arrays.stream(assign).max().getAsInt() >= min) return;
        if (cur == jobs.length) {
            int max = Arrays.stream(assign).max().getAsInt();
            if (max <= min) min = max;
            return;
        }
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            ids.add(i);
        }
        Collections.sort(ids, Comparator.comparingInt(o -> assign[o]));

        for (int i = 0; i < k; i++) {
            int id = ids.get(i);
            if (assign[id] + jobs[cur] < min && (i - 1 <= 0 || assign[id] != assign[ids.get(i - 1)])) {
                assign[id] += jobs[cur];
                dfs(jobs, cur + 1, k, assign);
                assign[id] -= jobs[cur];
            }

        }
    }
}
