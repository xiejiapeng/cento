package linshen.structure.unionfind;

/*
给你一个数组 arr ，该数组表示一个从 1 到 n 的数字排列。有一个长度为 n 的二进制字符串，该字符串上的所有位最初都设置为 0 。

在从 1 到 n 的每个步骤 i 中（假设二进制字符串和 arr 都是从 1 开始索引的情况下），二进制字符串上位于位置 arr[i] 的位将会设为 1 。

给你一个整数 m ，请你找出二进制字符串上存在长度为 m 的一组 1 的最后步骤。一组 1 是一个连续的、由 1 组成的子串，且左右两边不再有可以延伸的 1 。

返回存在长度 恰好 为 m 的 一组 1 的最后步骤。如果不存在这样的步骤，请返回 -1 。
 */

import java.util.HashMap;
import java.util.Map;

public class L1562 {
    public static void main(String[] args) {
        System.out.println(new L1562().findLatestStep(new int[]{3, 5, 1, 2, 4}, 1));
    }

    public int findLatestStep(int[] arr, int m) {
        int n = arr.length;
        int[] res = new int[n];
        UnionFild unionFild = new UnionFild(n, res);
        int last = -1;
        for (int i = 0; i < n; i++) {
            int t = arr[i] - 1;
            res[t] = 1;
            unionFild.sc.put(1, unionFild.sc.getOrDefault(1, 0) + 1);
            if (t - 1 >= 0 && res[t - 1] == 1) {
                unionFild.merge(t - 1, t);
            }
            if (t + 1 < n && res[t + 1] == 1) {
                unionFild.merge(t + 1, t);
            }
            if (unionFild.sc.getOrDefault(m, 0) >= 1) last = i;
        }
        return last == -1 ? -1 : last + 1;
    }

    static class UnionFild {
        Map<Integer, Integer> sc = new HashMap<>();
        private int[] parent;
        private int[] size;
        private int cnt;

        public UnionFild(int n, int[] res) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
            cnt = n;
        }

        public int find(int x) {
            if (parent[x] == x) return x;
            else {
                int t = find(parent[x]);
                parent[x] = t;
                return t;
            }
        }

        public void merge(int x, int y) {
            int px = find(x);
            int py = find(y);
            if (px == py) return;
            else {
                int u = size[py];
                int v = size[px];
                sc.put(u, sc.get(u) - 1);
                sc.put(v, sc.get(v) - 1);
                parent[px] = py;
                size[py] += size[px];
                cnt -= 1;
                sc.put(u + v, sc.getOrDefault(u + v, 0) + 1);
            }
        }

        public boolean same(int x, int y) {
            return find(x) == find(y);
        }
    }
}
