package linshen.dp;

/*
给你一个整数 n 和一个二维整数数组 queries。
有 n 个城市，编号从 0 到 n - 1。初始时，每个城市 i 都有一条单向道路通往城市 i + 1（ 0 <= i < n - 1）。
queries[i] = [ui, vi] 表示新建一条从城市 ui 到城市 vi 的单向道路。每次查询后，
你需要找到从城市 0 到城市 n - 1 的最短路径的长度。
返回一个数组 answer，对于范围 [0, queries.length - 1] 中的每个 i，
answer[i] 是处理完前 i + 1 个查询后，从城市 0 到城市 n - 1 的最短路径的长度。
提示：
3 <= n <= 500
1 <= queries.length <= 500
queries[i].length == 2
0 <= queries[i][0] < queries[i][1] < n
1 < queries[i][1] - queries[i][0]
查询中没有重复的道路。
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L3243 {
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++){
            ans[i] = shortestDistanceAfterQueries(n, queries, i);
        }
        return ans;
    }

    public int shortestDistanceAfterQueries(int n, int[][] queries, int k) {
        int[] dp = new int[n];
        Map<Integer, List<Integer>> edges = new HashMap<>();
        for (int i = 0; i <= k; i++){
            int x = queries[i][0];
            int y = queries[i][1];
            edges.putIfAbsent(x, new ArrayList<>());
            edges.get(x).add(y);
        }
        for (int i = n - 2; i > -1; i--){
            dp[i] = 1 + dp[i+1];
            if(edges.containsKey(i)) {
                List<Integer> ns = edges.get(i);
                for (int next : ns) {
                    dp[i] = Math.min(dp[i], 1 + dp[next]);
                }
            }
        }
        return dp[0];
    }
}
