package linshen.binary;

import java.util.*;

/*
给你一个 循环 数组 nums 和一个数组 queries 。

对于每个查询 i ，你需要找到以下内容：

数组 nums 中下标 queries[i] 处的元素与 任意 其他下标 j（满足 nums[j] == nums[queries[i]]）之间的 最小 距离。
如果不存在这样的下标 j，则该查询的结果为 -1 。
返回一个数组 answer，其大小与 queries 相同，其中 answer[i] 表示查询i的结果。
 */

public class L3488 {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        Map<Integer, List<Integer>> all = new HashMap<>();
        for (int i = 0; i < n; i++){
            all.putIfAbsent(nums[i], new ArrayList<>());
            all.get(nums[i]).add(i);
        }
        for (int x : all.keySet()) {
            List<Integer> a = all.get(x);
            Collections.sort(a);
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < queries.length; i++){
            int query = queries[i];
            List<Integer> idx = all.get(nums[query]);
            int id = id(idx, 0, idx.size()-1, query);
            if(idx.size() == 1)ans.add(-1);
            else {
                int x = (id-1+idx.size()) % (idx.size());
                int y = (id+1) %(idx.size());
                ans.add(Math.min(dist(n,idx.get(x), query), dist(n,idx.get(y), query)));
            }

        }
        return ans;
    }

    private int dist(int n, int a, int b) {
        if(a > b)return Math.min(a-b,b+n-a);
        else return Math.min(b-a, a+n-b);
    }

    private int id(List<Integer> idx, int left, int right, int target) {
        if(left == right)return left;
        else if(left == right - 1) {
            if(idx.get(left) == target)return left;
            else return right;
        } else {
            int mid = (left + right) / 2;
            if(idx.get(mid) == target)return mid;
            else if(idx.get(mid) > target)return id(idx, left, mid - 1, target);
            else return id(idx, mid + 1, right, target);
        }
    }

    public static void main(String[] args) {
        System.out.println(new L3488().solveQueries(new int[]{14,14,4,2,19,19,14,19,14}, new int[]{2,4,8,6,3}));
    }
}
