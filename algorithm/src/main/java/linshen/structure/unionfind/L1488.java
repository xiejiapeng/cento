package linshen.structure.unionfind;

/*
你的国家有无数个湖泊，所有湖泊一开始都是空的。当第 n 个湖泊下雨前是空的，那么它就会装满水。如果第 n 个湖泊下雨前是 满的 ，
这个湖泊会发生 洪水 。你的目标是避免任意一个湖泊发生洪水。

给你一个整数数组 rains ，其中：

rains[i] > 0 表示第 i 天时，第 rains[i] 个湖泊会下雨。
rains[i] == 0 表示第 i 天没有湖泊会下雨，你可以选择 一个 湖泊并 抽干 这个湖泊的水。
请返回一个数组 ans ，满足：

ans.length == rains.length
如果 rains[i] > 0 ，那么ans[i] == -1 。
如果 rains[i] == 0 ，ans[i] 是你第 i 天选择抽干的湖泊。
如果有多种可行解，请返回它们中的 任意一个 。如果没办法阻止洪水，请返回一个 空的数组 。

请注意，如果你选择抽干一个装满水的湖泊，它会变成一个空的湖泊。但如果你选择抽干一个空的湖泊，那么将无事发生。
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class L1488 {
    public static void main(String[] args) {
        //[-1,-1,2,1,-1,-1]
        System.out.println(Arrays.toString(new L1488().avoidFlood(new int[]{1, 2, 0, 0, 2, 1})));
    }

    //todo hhhhh 这类贪心回补的模板题一定要熟记
    public int[] avoidFlood(int[] rains) {
        TreeMap<Integer, Integer> drain = new TreeMap<>();
        Map<Integer, Integer> pool = new HashMap<>();
        int n = rains.length;
        int[] ans = new int[n];
        for (int i = 0; i < rains.length; i++) {
            if (rains[i] == 0) {
                drain.put(i, drain.getOrDefault(i, 0) + 1);
            } else {
                ans[i] = -1;
                int t = rains[i];
                if (!pool.containsKey(t)) {
                    pool.put(t, i);
                } else {
                    Integer s = drain.ceilingKey(pool.get(t));
                    if (s == null) {
                        return new int[0];
                    } else {
                        ans[s] = t;
                        drain.put(s, drain.get(s) - 1);
                        if (drain.get(s) == 0) drain.remove(s);
                        pool.put(t, i);
                    }
                }
            }
        }
        return ans;
    }
}
