package linshen.structure;

/*
给你一棵无根带权树，树中总共有 n 个节点，分别表示 n 个服务器，服务器从 0 到 n - 1 编号。
同时给你一个数组 edges ，其中 edges[i] = [ai, bi, weighti] 表示节点 ai 和 bi 之间有一条双向边，
边的权值为 weighti 。再给你一个整数 signalSpeed 。

如果两台服务器 a 和 b 是通过服务器 c 可连接的，则：

a < b ，a != c 且 b != c 。
从 c 到 a 的距离是可以被 signalSpeed 整除的。
从 c 到 b 的距离是可以被 signalSpeed 整除的。
从 c 到 b 的路径与从 c 到 a 的路径没有任何公共边。
请你返回一个长度为 n 的整数数组 count ，其中 count[i] 表示通过服务器 i 可连接 的服务器对的 数目 。
提示：

2 <= n <= 1000
edges.length == n - 1
edges[i].length == 3
0 <= ai, bi < n
edges[i] = [ai, bi, weighti]
1 <= weighti <= 106
1 <= signalSpeed <= 106
输入保证 edges 构成一棵合法的树。

todo hhhhh 很神奇，居然一次成功ac，我本来连调试都没有信心了。。。
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L3067 {
    public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        int n = edges.length + 1;
        int[] ans = new int[n];
        Map<Integer, Map<Integer, Integer>> dist = new HashMap<>();
        for (int i = 0; i < n; i++){
            dist.put(i, new HashMap<>());
        }
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            int z = edge[2];
            dist.get(x).put(y,z);
            dist.get(y).put(x,z);
        }

        for (int i = 0; i < n; i++){
            Map<Integer, Integer> neighbors = dist.get(i);
            List<Integer> cnt = new ArrayList<>();
            int a = 0;
            for (int y : neighbors.keySet()) {
                int z = neighbors.get(y);
                int t = dfs(y, i, z, signalSpeed, dist);
                for (int s : cnt){
                    a += t * s;
                }
                cnt.add(t);
            }
            ans[i] = a;
        }
        return ans;
    }

    private int dfs(int x, int parent, int dist, int mod, Map<Integer, Map<Integer, Integer>> neighbors) {
        if(neighbors.get(x).size() == 1) {
            if(dist % mod == 0)return 1;
            else return 0;
        } else {
            int ans = 0;
            for (int y : neighbors.get(x).keySet()) {
                if(y != parent) {
                    ans += dfs(y, x, dist + neighbors.get(x).get(y), mod, neighbors);
                }
            }
            if(dist % mod == 0)ans++;
            return ans;
        }
    }

}
