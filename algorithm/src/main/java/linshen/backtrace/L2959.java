package linshen.backtrace;

import java.util.*;

public class L2959 {
    int cnt = 0;

    public int numberOfSets(int n, int maxDistance, int[][] roads) {
        dfs(n, maxDistance, roads, 0, new HashSet<>());
        return cnt;
    }

    private void dfs(int n, int maxDistance, int[][] roads, int cur, Set<Integer> exclude) {
        if (cur == n) {
            if (isValid(n, maxDistance, roads, exclude)) cnt++;
            return;
        }

        // 1. 选择排除当前节点
        exclude.add(cur);
        dfs(n, maxDistance, roads, cur + 1, exclude);
        exclude.remove(cur);

        // 2. 不排除当前节点
        dfs(n, maxDistance, roads, cur + 1, exclude);
    }

    /*
        todo hhhhh
        Floyd-Warshall，即下面解法用于计算全局最短路径
        dijstra用于计算单源出发的最短路径
     */
    private boolean isValid(int n, int maxDistance, int[][] roads, Set<Integer> exclude) {
        // 初始化距离矩阵
        int INF = (int) 1e9;
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        // 构建当前保留节点的图
        for (int[] r : roads) {
            int u = r[0], v = r[1], w = r[2];
            if (exclude.contains(u) || exclude.contains(v)) continue;
            dist[u][v] = Math.min(dist[u][v], w);
            dist[v][u] = Math.min(dist[v][u], w);
        }

        // Floyd-Warshall 全局最短路
        for (int k = 0; k < n; k++) {
            if (exclude.contains(k)) continue;
            for (int i = 0; i < n; i++) {
                if (exclude.contains(i)) continue;
                for (int j = 0; j < n; j++) {
                    if (exclude.contains(j)) continue;
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // 检查连通性和最大距离限制
        for (int i = 0; i < n; i++) {
            if (exclude.contains(i)) continue;
            for (int j = 0; j < n; j++) {
                if (exclude.contains(j)) continue;
                if (dist[i][j] == INF) return false; // 不连通
                if (dist[i][j] > maxDistance) return false; // 超过 maxDistance
            }
        }

        return true;
    }

    public static void main(String[] args) {
        L2959 s = new L2959();
        int[][] roads = {{0,1,4},{1,2,5},{2,0,3}};
        System.out.println(s.numberOfSets(3, 5, roads)); // 示例输出
    }
}
