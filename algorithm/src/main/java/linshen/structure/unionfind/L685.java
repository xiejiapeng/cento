package linshen.structure.unionfind;

/*
在本问题中，有根树指满足以下条件的 有向 图。该树只有一个根节点，所有其他节点都是该根节点的后继。
该树除了根节点之外的每一个节点都有且只有一个父节点，而根节点没有父节点。

输入一个有向图，该图由一个有着 n 个节点（节点值不重复，从 1 到 n）的树及一条附加的有向边构成。
附加的边包含在 1 到 n 中的两个不同顶点间，这条附加的边不属于树中已存在的边。

结果图是一个以边组成的二维数组 edges 。 每个元素是一对 [ui, vi]，用以表示 有向 图中连接顶点 ui
和顶点 vi 的边，其中 ui 是 vi 的一个父节点。

返回一条能删除的边，使得剩下的图是有 n 个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案。
 */

import java.util.*;

public class L685 {
    //todo hhhhh 掌握使用并查集判断是否有环的技巧
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n + 1];
        int[] cand1 = null, cand2 = null;

        // Step 1: 找出入度为 2 的情况
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if (parent[v] == 0) {
                parent[v] = u;
            } else {
                cand1 = new int[]{parent[v], v};
                cand2 = new int[]{u, v};
                edge[1] = 0; // 临时无效化 cand2
            }
        }

        // Step 2: 并查集初始化
        int[] root = new int[n + 1];
        for (int i = 1; i <= n; i++) root[i] = i;

        // Step 3: 并查集检测环
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if (v == 0) continue; // 跳过 cand2

            int ru = find(root, u);
            if (ru == v) {
                // 检测到环
                if (cand1 != null) return cand1;
                return edge;
            }
            root[v] = ru; // union
        }

        // Step 4: 如果没环，则返回 cand2
        return cand2;
    }

    private int find(int[] root, int x) {
        if (root[x] != x) {
            root[x] = find(root, root[x]);
        }
        return root[x];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new L685().findRedundantDirectedConnection(new int[][]{{1, 2}, {1, 3}, {2, 3}})));
    }
}
