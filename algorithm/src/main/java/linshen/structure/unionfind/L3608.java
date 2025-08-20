package linshen.structure.unionfind;

/*
给你一个整数 n，表示一个包含 n 个节点（从 0 到 n - 1 编号）的无向图。该图由一个二维数组 edges 表示，
其中 edges[i] = [ui, vi, timei] 表示一条连接节点 ui 和节点 vi 的无向边，该边会在时间 timei 被移除。
同时，另给你一个整数 k。

最初，图可能是连通的，也可能是非连通的。你的任务是找到一个 最小 的时间 t，使得在移除所有满足条件 time <= t
的边之后，该图包含 至少 k 个连通分量。

* - * - * - *

返回这个 最小 时间 t。

连通分量 是图的一个子图，其中任意两个顶点之间都存在路径，且子图中的任意顶点均不与子图外的顶点共享边。
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class L3608 {
    static class UnionFild {
        private int[] parent;
        private int[] size;
        private int cnt;
        public UnionFild(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++){
                parent[i] = i;
                size[i] = 1;
            }
            cnt = n;
        }
        public int find(int x) {
            if(parent[x] == x)return x;
            else {
                int t = find(parent[x]);
                parent[x] = t;
                return t;
            }
        }

        public void merge(int x, int y) {
            int px = find(x);
            int py = find(y);
            if(px == py)return;
            else {
                parent[px] = py;
                size[py] += size[px];
                cnt -= 1;
            }
        }

        public boolean same(int x, int y) {
            return find(x) == find(y);
        }
    }
    public int minTime(int n, int[][] edges, int k) {
        UnionFild unionFild = new UnionFild(n);
        int m = edges.length;
        Arrays.sort(edges, Comparator.comparingInt(o -> o[2]));
        int last = -1;
        if(unionFild.cnt >= k)last = m;
        for (int j = m - 1; j > -1; j--){
            int[] edge = edges[j];
            int x = edge[0];
            int y = edge[1];
            unionFild.merge(x, y);
            if(unionFild.cnt >= k) {
                last = j;
            }
        }
        if(last == 0)return 0;
        return edges[last-1][2];
    }

    /*
        0  1
        2  3
     */
    public static void main(String[] args) {
        System.out.println(new L3608().minTime(3, new int[][]{{2,1,1469},{1,0,5701}}, 2));
//        System.out.println(new L3608().minTime(4, new int[][]{{3,2,9516},{2,0,8929},{1,0,15545}, {1,3,3234}}, 3));
    }
}
