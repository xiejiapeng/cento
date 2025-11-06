package linshen.structure.heap;

/*
给你一个整数 c，表示 c 个电站，每个电站有一个唯一标识符 id，从 1 到 c 编号。

这些电站通过 n 条 双向 电缆互相连接，表示为一个二维数组 connections，其中每个元素 connections[i] = [ui, vi] 表示电站 ui 和电站 vi 之间的连接。直接或间接连接的电站组成了一个 电网 。

最初，所有 电站均处于在线（正常运行）状态。

另给你一个二维数组 queries，其中每个查询属于以下 两种类型之一 ：

[1, x]：请求对电站 x 进行维护检查。如果电站 x 在线，则它自行解决检查。如果电站 x 已离线，则检查由与 x 同一 电网 中 编号最小 的在线电站解决。如果该电网中 不存在 任何 在线 电站，则返回 -1。

[2, x]：电站 x 离线（即变为非运行状态）。

返回一个整数数组，表示按照查询中出现的顺序，所有类型为 [1, x] 的查询结果。

注意：电网的结构是固定的；离线（非运行）的节点仍然属于其所在的电网，且离线操作不会改变电网的连接性。
 */

import java.util.*;

public class L3607 {
    static class UnionFind {
        int[] parent;
        int[] size;

        UnionFind(int n) {
            parent = new int[n + 1];
            size = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx == ry) return;
            if (size[rx] < size[ry]) {
                parent[rx] = ry;
                size[ry] += size[rx];
            } else {
                parent[ry] = rx;
                size[rx] += size[ry];
            }
        }
    }

    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        UnionFind uf = new UnionFind(c);

        // 1. 先构建电网（连通分量）
        for (int[] con : connections) {
            uf.union(con[0], con[1]);
        }

        // 2. 每个电网的在线集合（TreeSet 保证有序，取最小值 O(log n)）
        //todo hhhhh 构架完并查集后再建立pq，降低合并pq的开销
        Map<Integer, TreeSet<Integer>> compOnline = new HashMap<>();
        for (int i = 1; i <= c; i++) {
            int root = uf.find(i);
            compOnline.computeIfAbsent(root, k -> new TreeSet<>()).add(i);
        }

        // 3. 处理查询
        List<Integer> ans = new ArrayList<>();
        boolean[] offline = new boolean[c + 1]; // 节点是否离线

        for (int[] query : queries) {
            int type = query[0], x = query[1];
            int root = uf.find(x);

            if (type == 1) { // 维护检查
                if (!offline[x]) {
                    ans.add(x); // 自己在线
                } else {
                    TreeSet<Integer> set = compOnline.get(root);
                    if (set == null || set.isEmpty()) {
                        ans.add(-1);
                    } else {
                        ans.add(set.first()); // 电网最小在线节点
                    }
                }
            } else { // type == 2，电站离线
                if (!offline[x]) { // 避免重复下线
                    offline[x] = true;
                    compOnline.get(root).remove(x);
                }
            }
        }

        return ans.stream().mapToInt(i -> i).toArray();
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(new L3607().processQueries(4, new int[][]{{4, 3}, {3, 1}, {3, 2}, {4, 1}}, new int[][]{{2, 4}, {2, 2}, {1, 2}})));
    }
}
