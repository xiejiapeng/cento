package linshen.structure.unionfind;

/*
如果交换字符串 X 中的两个不同位置的字母，使得它和字符串 Y 相等，那么称 X 和 Y 两个字符串相似。
如果这两个字符串本身是相等的，那它们也是相似的。

例如，"tars" 和 "rats" 是相似的 (交换 0 与 2 的位置)； "rats" 和 "arts" 也是相似的，
但是 "star" 不与 "tars"，"rats"，或 "arts" 相似。

总之，它们通过相似性形成了两个关联组：{"tars", "rats", "arts"} 和 {"star"}。
注意，"tars" 和 "arts" 是在同一组中，即使它们并不相似。形式上，对每个组而言，要确定一个单词在组中，
只需要这个词和该组中至少一个单词相似。

给你一个字符串列表 strs。列表中的每个字符串都是 strs 中其它所有字符串的一个字母异位词。
请问 strs 中有多少个相似字符串组？

提示：

1 <= strs.length <= 300
1 <= strs[i].length <= 300
strs[i] 只包含小写字母。
strs 中的所有单词都具有相同的长度，且是彼此的字母异位词。
 */


import java.util.ArrayList;
import java.util.List;

public class L839 {
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
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        UnionFild all = new UnionFild(n);
        for (int i = 0; i < n; i++){
            for (int j = i + 1; j < n; j++){
                if(res(strs[i], strs[j]))all.merge(i, j);
            }
        }
        return all.cnt;
    }

    public boolean res(String x, String y) {
        List<Character> xx = new ArrayList<>();
        List<Character> yy = new ArrayList<>();
        for (int i = 0; i < x.length(); i++){
            if(x.charAt(i) != y.charAt(i)){
                xx.add(x.charAt(i));
                yy.add(y.charAt(i));
            }
            if(xx.size() > 2)return false;
        }
        if(xx.size() != 2)return false;
        return xx.get(0) == yy.get(1) && xx.get(1) == yy.get(0);
    }
}
