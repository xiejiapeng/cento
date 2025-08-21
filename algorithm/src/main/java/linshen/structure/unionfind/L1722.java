package linshen.structure.unionfind;

/*
给你两个整数数组 source 和 target ，长度都是 n 。还有一个数组 allowedSwaps ，其中每个 allowedSwaps[i] =
[ai, bi] 表示你可以交换数组 source 中下标为 ai 和 bi（下标从 0 开始）的两个元素。注意，你可以按 任意 顺序 多次
交换一对特定下标指向的元素。

相同长度的两个数组 source 和 target 间的 汉明距离 是元素不同的下标数量。形式上，其值等于满足 source[i] != target[i]
（下标从 0 开始）的下标 i（0 <= i <= n-1）的数量。

在对数组 source 执行 任意 数量的交换操作后，返回 source 和 target 间的 最小汉明距离 。
提示：

n == source.length == target.length
1 <= n <= 105
1 <= source[i], target[i] <= 105
0 <= allowedSwaps.length <= 105
allowedSwaps[i].length == 2
0 <= ai, bi <= n - 1
ai != bi
 */


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class L1722 {
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
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        UnionFild unionFild = new UnionFild(n);
        for (int[] s : allowedSwaps) {
            unionFild.merge(s[0], s[1]);
        }
        Map<Integer, Map<Integer, Integer>> cnt = new HashMap<>();
        for (int i = 0; i < n; i++){
            int f = unionFild.find(i);
            cnt.putIfAbsent(f, new HashMap<>());
            int s = source[i];
            cnt.get(f).put(s, cnt.get(f).getOrDefault(s, 0) + 1);
        }
        Set<Integer> used = new HashSet<>();
        int ans = 0;
        for (int i = 0; i < n; i++){
            int t = target[i];
            int f = unionFild.find(i);
            if(!cnt.get(f).containsKey(t))ans++;
            else {
                cnt.get(f).put(t, cnt.get(f).get(t)-1);
                if(cnt.get(f).get(t) == 0){
                    cnt.get(f).remove(t);
                }
            }
        }
        return ans;
    }
}
