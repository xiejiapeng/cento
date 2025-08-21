package linshen.structure.unionfind;

/*
树可以看成是一个连通且 无环 的 无向 图。

给定一个图，该图从一棵 n 个节点 (节点值 1～n) 的树中添加一条边后获得。添加的边的两个不同顶
点编号在 1 到 n 中间，且这条附加的边不属于树中已存在的边。图的信息记录于长度为 n 的
二维数组 edges ，edges[i] = [ai, bi] 表示图中在 ai 和 bi 之间存在一条边。

请找出一条可以删去的边，删除后可使得剩余部分是一个有着 n 个节点的树。如果有多个答案，
则返回数组 edges 中最后出现的那个。
提示:

n == edges.length
3 <= n <= 1000
edges[i].length == 2
1 <= ai < bi <= edges.length
ai != bi
edges 中无重复元素
给定的图是连通的
 */

import java.util.*;

public class L684 {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        Map<Integer, Set<Integer>> ne = new HashMap<>();
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            ne.putIfAbsent(x, new HashSet<>());
            ne.putIfAbsent(y, new HashSet<>());
            ne.get(x).add(y);
            ne.get(y).add(x);
        }
        Set<Integer> remain = new HashSet<>();
        for (int i = 1; i <= n; i++){
            remain.add(i);
        }
        Set<Integer> process = new HashSet<>();
        Set<Integer> candidate = new HashSet<>();
        for (int i = 1; i <= n; i++){
            if(ne.get(i).size() == 1){
                process.add(i);
            }
        }
        //todo hhh 拓扑排序；这里根据题意可以保证有环；一般情况下这里要额外考虑只剩三个或两个之类的特殊情况
        while (!process.isEmpty()) {
            for (int x : process) {
                remain.remove(x);
                int y = ne.get(x).stream().findFirst().get();
                ne.get(y).remove(x);
                if(ne.get(y).size() == 1){
                    candidate.add(y);
                }
            }
            process = candidate;
            candidate = new HashSet<>();
        }
        for (int i = n - 1; i > -1; i--){
            int x = edges[i][0];
            int y = edges[i][1];
            if(remain.contains(x) && remain.contains(y))return edges[i];
        }
        return null;
    }
}
