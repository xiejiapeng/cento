package linshen.structure.unionfind;

/*
n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。

如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。

给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，返回 可以移除的石子 的最大数量。
提示：

1 <= stones.length <= 1000
0 <= xi, yi <= 104
不会有两块石头放在同一个坐标点上
 */

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
/*
*   *
  *
*   *
 */

/*
* *
*   *
  * *
 */

public class L947 {
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
    public int removeStones(int[][] stones) {
        UnionFild all = new UnionFild(stones.length);
        //todo hhh 很简单的一题，不要复杂化了；多画画图构造并查集
        for (int i = 0; i < stones.length; i++){
            for (int j = i + 1; j < stones.length; j++){
                if(stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    all.merge(i, j);
                }
            }
        }
        return stones.length - all.cnt;
    }

    public static void main(String[] args) {
        System.out.println(new L947().removeStones(new int[][]{{0,0},{0,2},{1,1},{2,0},{2,2}}));
    }
}
