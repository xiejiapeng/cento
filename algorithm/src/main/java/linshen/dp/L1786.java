package linshen.dp;

import java.util.*;

/*
现有一个加权无向连通图。给你一个正整数 n ，表示图中有 n 个节点，
并按从 1 到 n 给节点编号；另给你一个数组 edges ，其中每个 edges[i] = [ui, vi, weighti]
表示存在一条位于节点 ui 和 vi 之间的边，这条边的权重为 weighti 。
从节点 start 出发到节点 end 的路径是一个形如 [z0, z1, z2, ..., zk] 的节点序列，
满足 z0 = start 、zk = end 且在所有符合 0 <= i <= k-1 的节点 zi 和 zi+1 之间存在一条边。
路径的距离定义为这条路径上所有边的权重总和。
用 distanceToLastNode(x) 表示节点 n 和 x 之间路径的最短距离。
受限路径 为满足 distanceToLastNode(zi) > distanceToLastNode(zi+1)
的一条路径，其中 0 <= i <= k-1 。
返回从节点 1 出发到节点 n 的 受限路径数 。由于数字可能很大，请返回对 109 + 7 取余 的结果。

todo hhhhhhh 记住吧，dijkstra
 */
public class L1786 {
    int mod = (int)(1e9+7);
    public int countRestrictedPaths(int n, int[][] edges) {
        Map<Integer, Map<Integer, Integer>> neighbors = new HashMap<>();
        Map<Integer, Long> ans = new HashMap<>();
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            neighbors.putIfAbsent(x, new HashMap<>());
            neighbors.get(x).put(y, edge[2]);
            neighbors.putIfAbsent(y, new HashMap<>());
            neighbors.get(y).put(x, edge[2]);
        }
        int[] dist = new int[n+1];
        //todo hhhh dijkstra经典的两个集合
        Set<Integer> determined = new HashSet<>();
        //todo  hhhh queue是优先级队列，用来排序，candidate的value对应于queue的元素。保存queue中元素的索引也方便我们
        //todo hhhhh 删除queue中的元素；像treemap无法对value排序，通过这种方式可以实现基于value排序！！！！
        Map<Integer,int[]> candidate = new HashMap<>();
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        determined.add(n);
        ans.put(n, 1L);
        for (int x : neighbors.get(n).keySet()){
            int d = neighbors.get(n).get(x);
            candidate.put(x, new int[]{x,d});
            queue.add(candidate.get(x));
        }
        while (!queue.isEmpty()) {
            int[] t = queue.poll();
            int x = t[0];
            int d = t[1];
            dist[x] = d;
            candidate.remove(x);
            determined.add(x);
            long a = 0;
            //todo hhh，注意遍历neighbors判断determined和遍历determined判断neighbors的时间复杂度可能不相同
            for (int y : neighbors.get(x).keySet()) {
                //todo hhh dijkstra已经保证了距离短的先进determined，为什么还有判断dist[x] > dist[y]？这是因为存在dist[x] == dist[y]的情况
                if (determined.contains(y) && dist[x] > dist[y]) {
                    a += ans.getOrDefault(y, 0L);
                    a %= mod;
                }
            }
            ans.put(x, a);
            Map<Integer, Integer> nei = neighbors.get(x);
            for (int y : nei.keySet()) {
                if(!determined.contains(y)) {
                    int td = d + nei.get(y);
                    if(candidate.containsKey(y)) {
                        if(candidate.get(y)[1] > td) {
                            queue.remove(candidate.get(y));
                            candidate.put(y, new int[]{y, td});
                            queue.add(candidate.get(y));
                        }
                    } else {
                        candidate.put(y, new int[]{y, td});
                        queue.add(candidate.get(y));
                    }
                }
            }
        }

//        System.out.println(Arrays.toString(dist));
        return (int)(ans.get(1) % mod);
    }

    public static void main(String[] args) {
        int[][] edges = new int[][]{{1,2,3},{1,3,3},{2,3,1},{1,4,2},{5,2,2},{3,5,1},{5,4,10}};
        /*
        输入：n = 5, edges = [[1,2,3],[1,3,3],[2,3,1],[1,4,2],[5,2,2],[3,5,1],[5,4,10]] 输出：3
         */
        System.out.println(new L1786().countRestrictedPaths(5, edges));
    }
}
