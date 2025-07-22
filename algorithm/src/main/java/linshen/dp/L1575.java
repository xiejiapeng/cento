package linshen.dp;

/*
给你一个 互不相同 的整数数组，其中 locations[i] 表示第 i 个城市的位置。
同时给你 start，finish 和 fuel 分别表示出发城市、目的地城市和你初始拥有的汽油总量

每一步中，如果你在城市 i ，你可以选择任意一个城市 j ，满足 j != i 且 0 <= j < locations.length ，并移动到城市 j 。从城市 i 移动到 j 消耗的汽油量为 |locations[i] - locations[j]|，|x| 表示 x 的绝对值。

请注意， fuel 任何时刻都 不能 为负，且你 可以 经过任意城市超过一次（包括 start 和 finish ）。

请你返回从 start 到 finish 所有可能路径的数目。

由于答案可能很大， 请将它对 10^9 + 7 取余后返回。
 */

import java.util.HashMap;
import java.util.Map;

public class L1575 {
    int mod = (int)(1e9+7);
    Map<Integer, Map<Integer, Long>> tmp = new HashMap<>();
    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        int n = locations.length;
        for (int i = 0; i < n; i++){
            tmp.put(i, new HashMap<>());
        }
        return count(locations, start, finish, fuel);
    }

    public int count(int[] locations, int start, int finish, int fuel) {
        if(tmp.get(start).containsKey(fuel)){
            return (int)(tmp.get(start).get(fuel) % mod);
        } else {
            if(fuel == 0) {
                if(start == finish)tmp.get(start).put(fuel, 1L);
                else tmp.get(start).put(fuel, 0L);
            } else {
                //todo m 注意到了finish也不要停止搜索
                long t = (start == finish ? 1 : 0);
                for (int i = 0; i < locations.length; i++){
                    if(i == start)continue;
                    if(Math.abs(locations[start] - locations[i]) <= fuel) {
                        t += (count(locations, i, finish, fuel - Math.abs(locations[start] - locations[i])) % mod);
                    }
                }
                tmp.get(start).put(fuel, t % mod);

            }
            return (int)(tmp.get(start).get(fuel) % mod);
        }
    }

    public static void main(String[] args) {
        System.out.println(new L1575().countRoutes(new int[]{2,3,6,8,4}, 1,3,5));
    }
}
