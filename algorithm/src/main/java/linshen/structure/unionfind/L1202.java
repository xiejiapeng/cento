package linshen.structure.unionfind;

/*
给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的
两个索引（编号从 0 开始）。

你可以 任意多次交换 在 pairs 中任意一对索引处的字符。

返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class L1202 {
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

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        UnionFild unionFild = new UnionFild(n);
        for (List<Integer> p : pairs) {
            unionFild.merge(p.get(0), p.get(1));
        }
        //todo hhh 请克制住将queue定义在每个find元的冲动，那样会导致合并的时候对queue读来读去；我们只需要在最后一次性构造好queue就行
        //遇到元素之间有两两关系的，一定要考虑用并查集
        Map<Integer, PriorityQueue<Character>> all = new HashMap<>();
        for (int i = 0; i < n; i++){
            int f = unionFild.find(i);
            all.putIfAbsent(f, new PriorityQueue<>());
            all.get(f).add(s.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++){
            int f = unionFild.find(i);
            sb.append(all.get(f).poll());
        }
        return sb.toString();
    }
}
