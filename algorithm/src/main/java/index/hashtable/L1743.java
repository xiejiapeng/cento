package index.hashtable;

/*
存在一个由 n 个不同元素组成的整数数组 nums ，但你已经记不清具体内容。好在你还记得 nums 中的每一对相邻元素。

给你一个二维整数数组 adjacentPairs ，大小为 n - 1 ，其中每个 adjacentPairs[i] = [ui, vi] 表示元素 ui 和 vi 在 nums 中相邻。

题目数据保证所有由元素 nums[i] 和 nums[i+1] 组成的相邻元素对都存在于 adjacentPairs 中，存在形式可能是 [nums[i], nums[i+1]] ，也可能是 [nums[i+1], nums[i]] 。这些相邻元素对可以 按任意顺序 出现。

返回 原始数组 nums 。如果存在多种解答，返回 其中任意一个 即可。
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L1743 {
    public int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer, List<Integer>> all = new HashMap<>();
        for (int[] adjs : adjacentPairs) {
            int x = adjs[0];
            int y = adjs[1];
            all.putIfAbsent(x, new ArrayList<>());
            all.get(x).add(y);
            all.putIfAbsent(y, new ArrayList<>());
            all.get(y).add(x);
        }
        int n = adjacentPairs.length + 1;
        int[] ans = new int[n];
        for (int x : all.keySet()) {
            if (all.get(x).size() == 1) {
                ans[0] = x;
                break;
            }
        }
        int i = 1;
        int last = Integer.MIN_VALUE;
        int cur = ans[0];
        while (i < n) {
            List<Integer> a = all.get(cur);
            int s = a.get(0) == last ? a.get(1) : a.get(0);
            ans[i++] = s;
            last = cur;
            cur = s;
        }
        return ans;
    }
}
