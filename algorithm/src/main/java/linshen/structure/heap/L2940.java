package linshen.structure.heap;

/*
给你一个下标从 0 开始的正整数数组 heights ，其中 heights[i] 表示第 i 栋建筑的高度。

如果一个人在建筑 i ，且存在 i < j 的建筑 j 满足 heights[i] < heights[j] ，那么这个人可以移动到建筑 j 。

给你另外一个数组 queries ，其中 queries[i] = [ai, bi] 。第 i 个查询中，Alice 在建筑 ai ，Bob 在建筑 bi 。

请你能返回一个数组 ans ，其中 ans[i] 是第 i 个查询中，Alice 和 Bob 可以相遇的 最左边的建筑 。
如果对于查询 i ，Alice 和 Bob 不能相遇，令 ans[i] 为 -1 。
 */

import java.util.ArrayList;
import java.util.List;

public class L2940 {
    //todo hhhh cv的答案，将query分散到每个i上，然后就只用遍历i，不用遍历query了；遍历i时利用到i拥有的单调栈即可
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int n = heights.length;
        int m = queries.length;
        List<int[]>[] query = new List[n];
        for (int i = 0; i < n; i++) {
            query[i] = new ArrayList<int[]>();
        }
        int[] ans = new int[m];
        List<Integer> st = new ArrayList<Integer>();

        for (int i = 0; i < m; i++) {
            int a = queries[i][0];
            int b = queries[i][1];
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            if (a == b || heights[a] < heights[b]) {
                ans[i] = b;
                continue;
            }
            query[b].add(new int[]{i, heights[a]});
        }

        int top = -1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < query[i].size(); j++) {
                int q = query[i].get(j)[0];
                int val = query[i].get(j)[1];
                if (top == -1 || heights[st.get(0)] <= val) {
                    ans[q] = -1;
                    continue;
                }

                int l = 0, r = top;
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (heights[st.get(mid)] > val) {
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                ans[q] = st.get(r);
            }

            while (top >= 0 && heights[st.get(top)] <= heights[i]) {
                st.remove(st.size() - 1);
                top--;
            }
            st.add(i);
            top++;
        }
        return ans;
    }
}
