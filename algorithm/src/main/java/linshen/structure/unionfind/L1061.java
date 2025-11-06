package linshen.structure.unionfind;

/*
给出长度相同的两个字符串s1 和 s2 ，还有一个字符串 baseStr 。

其中 s1[i] 和 s2[i] 是一组等价字符。

举个例子，如果 s1 = "abc" 且 s2 = "cde"，那么就有 'a' == 'c', 'b' == 'd', 'c' == 'e'。
等价字符遵循任何等价关系的一般规则：

自反性 ：'a' == 'a'
对称性 ：'a' == 'b' 则必定有 'b' == 'a'
传递性 ：'a' == 'b' 且 'b' == 'c' 就表明 'a' == 'c'
例如， s1 = "abc" 和 s2 = "cde" 的等价信息和之前的例子一样，那么 baseStr = "eed" , "acd" 或 "aab"，这三个字符串都是等价的，而 "aab" 是 baseStr 的按字典序最小的等价字符串

利用 s1 和 s2 的等价信息，找出并返回 baseStr 的按字典序排列最小的等价字符串。
提示：

1 <= s1.length, s2.length, baseStr <= 1000
s1.length == s2.length
字符串s1, s2, and baseStr 仅由从 'a' 到 'z' 的小写英文字母组成。
 */

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class L1061 {
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

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        UnionFild unionFild = new UnionFild(26);
        for (int i = 0; i < s1.length(); i++){
            unionFild.merge(s1.charAt(i)-'a', s2.charAt(i)-'a');
        }
        Map<Integer, TreeSet<Character>> all = new HashMap<>();
        for (int i = 0; i < 26; i++){
            int f = unionFild.find(i);
            all.putIfAbsent(f, new TreeSet<>());
            all.get(f).add((char)('a' + i));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < baseStr.length(); i++){
            int f = unionFild.find(baseStr.charAt(i)-'a');
            sb.append(all.get(f).first());
        }
        return sb.toString();
    }
}
