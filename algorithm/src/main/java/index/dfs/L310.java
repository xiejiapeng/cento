package index.dfs;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/*
树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，任何一个没有简单环路的连通图都是一棵树。

给你一棵包含 n 个节点的树，标记为 0 到 n - 1 。给定数字 n 和一个有 n - 1 条无向边的 edges 列表（每一个边都是一对标签），其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条无向边。

可选择树中任何一个节点作为根。当选择节点 x 作为根节点时，设结果树的高度为 h 。在所有可能的树中，具有最小高度的树（即，min(h)）被称为 最小高度树 。

请你找到所有的 最小高度树 并按 任意顺序 返回它们的根节点标签列表。

树的 高度 是指根节点和叶子节点之间最长向下路径上边的数量。

 4
5 3
    0 1 2
 */

public class L310 {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Map<Integer,Set<Integer>> neighbors = new HashMap<>();
        if(n == 1)return Arrays.asList(0);
        Set<Integer> all = new HashSet<>();
        for (int i = 0; i < n; i++) {
            neighbors.put(i, new HashSet<>());
            all.add(i);
        }

        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            neighbors.get(x).add(y);
            neighbors.get(y).add(x);
        }
        Set<Integer> leaves = new HashSet<>();
        for (int i = 0; i < n; i++){
            if(neighbors.get(i).size() == 1) {
                leaves.add(i);
            }
        }
        while (!leaves.isEmpty()) {
            if(all.size() <= 2) {
                return new ArrayList<>(all);
            }
            Set<Integer> nl = new HashSet<>();
            Set<Integer> tl = new HashSet<>(leaves);
            for (int x : tl) {
                all.remove(x);
                for (int y : neighbors.get(x)) {
                    neighbors.get(x).remove(y);
                    neighbors.get(y).remove(x);
                    if(neighbors.get(y).size() == 1){
                        nl.add(y);
                    }
                }
            }
            leaves = nl;
        }

        return new ArrayList<>();

    }

    public static void main(String[] args) {
        int[][] edges = {{3,0},{3,1},{3,2},{3,4},{5,4}};
        System.out.println(new L310().findMinHeightTrees(6, edges));
    }
}
