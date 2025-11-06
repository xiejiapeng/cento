package linshen.structure.unionfind;

/*
给你一个二维整数数组 properties，其维度为 n x m，以及一个整数 k。

定义一个函数 intersect(a, b)，它返回数组 a 和 b 中 共有的不同整数的数量 。

构造一个 无向图，其中每个索引 i 对应 properties[i]。如果且仅当 intersect(properties[i], properties[j]) >= k
（其中 i 和 j 的范围为 [0, n - 1] 且 i != j），节点 i 和节点 j 之间有一条边。

返回结果图中 连通分量 的数量。
提示：
1 <= n == properties.length <= 100
1 <= m == properties[i].length <= 100
1 <= properties[i][j] <= 100
1 <= k <= m
 */

import java.util.HashSet;
import java.util.Set;

public class L3493 {
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
                //todo hhh 记住，并查集操作大多数先要做find，然后基于find的元操作
                parent[px] = py;
                size[py] += size[px];
                cnt -= 1;
            }
        }

        public boolean same(int x, int y) {
            return find(x) == find(y);
        }
    }

    //todo hhhhh 测试了很久很久；不要纠结并查集的分离方法，只需要从后往前就可以模拟这个过程！！涉及到联通度要么正向要么反向
    public int numberOfComponents(int[][] properties, int k) {
        int n = properties.length;
        int m = properties[0].length;
        UnionFild unionFild = new UnionFild(n);
        Set<Integer>[] ps = new Set[n];
        for (int i = 0; i < n; i++){
            Set<Integer> s = new HashSet<>();
            for (int j = 0; j < m; j++){
                s.add(properties[i][j]);
            }
            ps[i] = s;
        }
        for (int i = 0; i < n; i++){
            for (int j = i + 1; j < n; j++){
                if(intersect(ps[i], ps[j]) >= k)unionFild.merge(i,j);
            }
        }
        return unionFild.cnt;
    }

    private int intersect(Set<Integer> x, Set<Integer> y) {
        int ans = 0;
        for (int xx : x) {
            if(y.contains(xx))ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new L3493().numberOfComponents(new int[][]{{1},{1},{1}}, 1));
    }
}
